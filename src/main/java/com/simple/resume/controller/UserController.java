package com.simple.resume.controller;

import com.simple.resume.VO.UserInfoVO;
import com.simple.resume.common.*;
import com.simple.resume.pojo.Resume;
import com.simple.resume.pojo.User;
import com.simple.resume.service.ResumeService;
import com.simple.resume.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ResumeService resumeService;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 用户注册,需要发送邮件到用户邮箱
     * @param user
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String saveUser(User user) {
        user.setActiveCode(MD5Util.encode2hex(user.getEmail()));
        user.setPassword(MD5Util.encode2hex(user.getPassword()));
        try {
            userService.saveUser(user);
            ///邮件的内容
            StringBuffer sb = new StringBuffer("<h2>简历管理系统</h2>请点击下面的链接激活账号:<br>");
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


    /**
     * 用户登录，包含remember me功能，使用cookie实现
     * @param userID
     * @param password
     * @param rememberMe
     * @param response
     * @return
     * @throws IOException
     */
    @PostMapping(value = "login", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkLogin(@RequestParam("userID") int userID, @RequestParam("password") String password,
                             @RequestParam("rememberMe") Boolean rememberMe, HttpServletResponse response) throws IOException {

        JSON json = null;
        User user = userService.findByUserIDandPassword(userID, MD5Util.encode2hex(password));
        if (user == null) {
            json = JSONSerializer.toJSON(
                    new JsonResult<User>(1, "用户ID或者密码错误！", null));
            return json.toString();
        } else if (user.getPermission() == 1) {
            //permission为1表示管理员
            user.setIsLogined(1);
            userService.updateUser(user);
            request.getSession().setAttribute("userID", userID);
            //如果用户选择了记住我功能，就生成一个加密的token作为cookie存在用户的浏览器
            if (rememberMe) {
                String token = jwtUtils.createJWT(userID, user.getUserName(), 7 * 24);
                Cookie userToken = new Cookie("userToken", token);
                userToken.setMaxAge(60 * 60 * 24 * 7);
                userToken.setPath("/");
                response.addCookie(userToken);
            }
            json = JSONSerializer.toJSON(new JsonResult<User>(5, "登录成功！", user));
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
                //如果当前在同一个浏览器窗口已经有用户登录了，则提示用户先登出已有的用户(技术有限!)
                Integer current = (Integer) request.getSession().getAttribute("userID");
                if (current != null) {
                    json = JSONSerializer.toJSON(
                            new JsonResult<User>(6, "不允许在同一个浏览器窗口(不同标签页)登录多个用户,请先登出已有的用户!", null));
                    return json.toString();
                }
                //对当前登录用户的处理
                user.setIsLogined(1);
                userService.updateUser(user);
                request.getSession().setAttribute("userID", userID);
                if (rememberMe) {
                    String token = jwtUtils.createJWT(userID, user.getUserName(), 7 * 24);
                    Cookie userToken = new Cookie("userToken", token);
                    userToken.setMaxAge(60 * 60 * 24 * 7);
                    userToken.setPath("/");
                    response.addCookie(userToken);
                }
                json = JSONSerializer.toJSON(new JsonResult<User>(0, "登录成功！", user));
            }
        }
        return json.toString();
    }

    /**
     * 用户登出
     * @param userID
     * @param response
     * @return
     */
    @RequestMapping("logout")
    @ResponseBody
    public String logout(@RequestParam("userID") int userID, HttpServletResponse response) {
        User user = new User();
        user.setUserID(userID);
        user.setIsLogined(0);
        userService.updateUser(user);
        //销毁session
        request.getSession().invalidate();

        Cookie userToken = new Cookie("userToken", null);
        userToken.setMaxAge(0); //删除该Cookie
        userToken.setPath("/");
        response.addCookie(userToken);

        JSON json = JSONSerializer.toJSON(new JsonResult());
        return json.toString();
    }

    /**
     * 用户邮箱修改密码
     * @param userID
     * @param password
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    @ResponseBody
    public void changePassword(@RequestParam("userID") int userID, @RequestParam("password") String password) {
        User user = new User();
        user.setUserID(userID);
        user.setPassword(MD5Util.encode2hex(password));
        userService.updateUser(user);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "updateUser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateUser(User user) {
        user.setUpdateTime(Timestamp.valueOf(df.format(new Date())));
        userService.updateUser(user);
        return new JsonResult().toString();
    }

    /**
     * 用户正常修改密码
     * @param userID
     * @param password
     * @param newpassword
     * @return
     */
    @RequestMapping(value = "chg_passwd", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String chg_passwd(@RequestParam("userID") Integer userID,
                             @RequestParam("password") String password,
                             @RequestParam("newpassword") String newpassword) {
        User user = userService.findByUserIDandPassword(userID, MD5Util.encode2hex(password));
        if (user == null) {
            JSON json = JSONSerializer.toJSON(new JsonResult<User>(1, "原密码错误!", null));
            return json.toString();
        }
        user.setUpdateTime(Timestamp.valueOf(df.format(new Date())));
        user.setPassword(MD5Util.encode2hex(newpassword));
        userService.updateUser(user);
        JSON json = JSONSerializer.toJSON(new JsonResult<User>(0, "修改密码成功!", null));
        return json.toString();
    }

    /**
     * 显示用户列表
     * @return
     */
    @RequestMapping(value = "userList", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String userList() {
        List<User> allUser = userService.findAllUser();
        List<UserInfoVO> userInfoVOs = new ArrayList<>();
        for (User user : allUser) {
            UserInfoVO userInfoVO = new UserInfoVO();
            try {
                userInfoVO.setCreate_time(user.getCreateTime().toString().substring(0, user.getCreateTime().toString().length() - 2));
                userInfoVO.setS_sex(user.getSex() == 0 ? "男" : "女");
                userInfoVO.setA_activeStatus(user.getActiveStatus() == 0 ? "否" : "是");
                BeanUtils.copyProperties(userInfoVO, user);
                Resume resume = resumeService.findByUserID(user.getUserID());
                if (resume == null || resume.getStatus() == 3) {
                    userInfoVO.setIsDeliver("否");
                } else {
                    userInfoVO.setIsDeliver("是");
                }
                userInfoVOs.add(userInfoVO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JSON json = JSONSerializer.toJSON(new JsonResult<List<UserInfoVO>>(0, "查询用户列表成功", userInfoVOs));
        return json.toString();
    }
}
