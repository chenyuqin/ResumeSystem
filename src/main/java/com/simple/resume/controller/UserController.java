package com.simple.resume.controller;

import com.simple.resume.common.*;
import com.simple.resume.pojo.User;
import com.simple.resume.service.UserService;
import io.jsonwebtoken.Claims;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("user/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    JwtUtils jwtUtils;

    //用户注册
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String saveUser(User user) {
        user.setActiveCode(MD5Util.encode2hex(user.getEmail()));
        try {
            userService.saveUser(user);
            ///邮件的内容
            StringBuffer sb=new StringBuffer("<h2>简历管理系统</h2>请点击下面的链接激活账号:<br>");
            sb.append("http://127.0.0.1:8090/common/active?email=");
            sb.append(user.getEmail());
            sb.append("&activeCode=");
            sb.append(user.getActiveCode());
            sb.append("");
            //发送邮件
            SendEmail.send(user.getEmail(), sb.toString());
            System.out.println("发送邮件");

            JSON json = JSONSerializer.toJSON(new JsonResult<User>(0, "注册成功，请到邮箱中激活账号！", null));
            return json.toString();
        } catch (Exception e) {
            JSON json = JSONSerializer.toJSON(new JsonResult<User>(1, "注册失败，请检查您的输入是否有误！", null));
            return json.toString();
        }
    }



    //用户登录
    @PostMapping(value = "login", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkLogin(@RequestParam("userID") int userID, @RequestParam("password") String password,
                             @RequestParam("rememberMe") Boolean rememberMe, HttpServletResponse response) throws IOException {

        JSON json = null;
        User user = userService.findByUserIDandPassword(userID, password);
        if (user == null) {
            json = JSONSerializer.toJSON(
                    new JsonResult<User>(1, "用户ID或者密码错误！", null));
            return json.toString();
        } else {
            if (user.getActiveStatus() == 0) {
                json = JSONSerializer.toJSON(
                        new JsonResult<User>(2, "用户未激活，请先到邮箱激活再说!", null));
                return json.toString();
            } else if (user.getIsLogined() == 1) {
                json = JSONSerializer.toJSON(
                        new JsonResult<User>(3, "用户已登录，不允许重复登录!", null));
                return json.toString();
            } else {
                //如果当前已经有用户登录了
                Integer current = (Integer)request.getSession().getAttribute("userID");
                if (current != null) {
                    User currentUser = new User();
                    currentUser.setUserID(current);
                    currentUser.setIsLogined(0);
                    userService.updateUser(currentUser);
                }
                //对当前登录用户的处理
                user.setIsLogined(1);
                userService.updateUser(user);
                request.getSession().setAttribute("userID", userID);
                if (rememberMe) {
                    String token = jwtUtils.createJWT(userID, user.getUserName(), 7 * 24);
                    Cookie userToken = new Cookie("userToken", token);
                    userToken.setMaxAge(60*60*24*7);
                    userToken.setPath("/");
                    response.addCookie(userToken);
                }
                json = JSONSerializer.toJSON(new JsonResult<User>(0, "登录成功！", user));
            }
        }
        return json.toString();
    }

    //用户退出
    @RequestMapping("logout")
    @ResponseBody
    public String logout(@RequestParam("userID") int userID, HttpServletResponse response) {
        User user = new User();
        user.setUserID(userID);
        user.setIsLogined(0);
        userService.updateUser(user);
        //销毁session
        request.getSession().invalidate();

        Cookie userToken=new Cookie("userToken",null);
        userToken.setMaxAge(0); //删除该Cookie
        userToken.setPath("/");
        response.addCookie(userToken);

        JSON json = JSONSerializer.toJSON(new JsonResult());
        return json.toString();
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    @ResponseBody
    public void changePassword(@RequestParam("userID") int userID, @RequestParam("password") String password) {
        User user = new User();
        user.setUserID(userID);
        user.setPassword(password);
        userService.updateUser(user);
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateUser(User user) {
        user.setUpdateTime(new Date());
        userService.updateUser(user);
        return new JsonResult().toString();
    }

    @RequestMapping(value = "chg_passwd", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String chg_passwd(@RequestParam("userID") Integer userID,
                             @RequestParam("password") String password,
                             @RequestParam("newpassword") String newpassword) {
        User user = userService.findByUserIDandPassword(userID, password);
        if (user == null) {
            JSON json = JSONSerializer.toJSON(new JsonResult<User>(1, "原密码错误!", null));
            return json.toString();
        }
        user.setUpdateTime(new Date());
        user.setPassword(newpassword);
        userService.updateUser(user);
        JSON json = JSONSerializer.toJSON(new JsonResult<User>(0, "修改密码成功!", null));
        return json.toString();
    }
}
