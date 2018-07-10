package com.simple.resume.controller;

import com.simple.resume.common.JsonResult;
import com.simple.resume.common.JwtUtils;
import com.simple.resume.common.SendEmail;
import com.simple.resume.common.StatusEnum;
import com.simple.resume.pojo.User;
import com.simple.resume.service.UserService;
import io.jsonwebtoken.Claims;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 由于使用cookie的机制实现remember me功能，
 * 所以不能拦截login和register页面所有验证之类的接口，所以单独写一个controller声明这类接口,
 * 不然会导致GET 405问题
 */
@Controller
@RequestMapping("common/")
public class NotInterceptController {
    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    JwtUtils jwtUtils;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //ajax异步检测注册的userID是否存在
    @RequestMapping(value = "checkUserID", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkUserID(@RequestParam("userID") int userID) {
        User user = userService.findByUserID(userID);
        JSON json = null;
        if (user == null) {
            json = JSONSerializer.toJSON(new JsonResult());
        } else {
            json = JSONSerializer.toJSON(new JsonResult<User>(1,"", user));
        }
        return json.toString();
    }

    //ajax异步检测注册的email是否存在
    @RequestMapping(value = "checkEmail", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkUserID(@RequestParam("email") String email) {
        User user = userService.findByEmail(email);
        JSON json = null;
        if (user == null) {
            json = JSONSerializer.toJSON(new JsonResult());
        } else {
            json = JSONSerializer.toJSON(new JsonResult(1));
        }
        return json.toString();
    }

    //验证用户点击激活链接后的邮箱和激活码是否正确，决定用户是否激活
    @RequestMapping("active")
    public String active(@RequestParam("email") String email, @RequestParam("activeCode") String activeCode) {
        User user = userService.findByEmail(email);
        if (user != null) {
            if (user.getActiveStatus() == 0) {
                if (activeCode.equals(user.getActiveCode())) {
                    user.setActiveStatus(1);
                    user.setActiveTime(Timestamp.valueOf(df.format(new Date())));
                    userService.updateUser(user);
                    return "success";
                }
                return "failed";
            }
        }
        return "failed";
    }

    //忘记密码:发送一封邮件给用户，邮件中包含修改密码的链接
    @PostMapping(value = "forgetPassword", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String forgetPassword(@RequestParam("email") String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            JSON json = JSONSerializer.toJSON(new JsonResult<User>(StatusEnum.EmailNotExist.getCode(),
                    "邮箱未注册！", null));
            return json.toString();
        } else {
            //生产一个token用于验证用户是否可以免原密码修改密码，类似激活
            String token = jwtUtils.createJWT(user.getUserID(), user.getUserName(), 1);
            ///邮件的内容
            StringBuffer sb=new StringBuffer("<h2>简历管理系统</h2>请点击下面的链接前往修改密码(1小时内有效):<br>");
            sb.append("<a href=\"http://127.0.0.1:8090/common/alter?token=" + token + "\">前往修改密码</a>");
            sb.append("");
            //发送邮件
            SendEmail.send(user.getEmail(), sb.toString());
            JSON json = JSONSerializer.toJSON(new JsonResult<User>(StatusEnum.SUCCESS.getCode(),
                    "邮件发送成功,请到邮箱中验证并修改密码！", null));
            return json.toString();
        }
    }

    //验证用户邮箱中的修改密码邮件里的接口
    @RequestMapping("alter")
    public String alter(@RequestParam("token") String token, Model model,
                        HttpServletResponse response) throws IOException {
        //验证url，一般会有对应的邮件服务器和数据库表记录信息（每个链接只能使用一遍，且以最新的为标准），
        //url一般会有用户id、过期时间和随机值，使用md5加密
        Claims claims = jwtUtils.parseJWT(token);
        if (claims == null) {
            return "error";
        } else {
            Integer userID = (Integer) claims.get("userID");
            return "redirect:/alterPassword.html?userID=" + userID;
        }
    }
}
