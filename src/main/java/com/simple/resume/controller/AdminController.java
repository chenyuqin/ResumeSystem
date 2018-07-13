package com.simple.resume.controller;

import com.simple.resume.common.JsonResult;
import com.simple.resume.pojo.User;
import com.simple.resume.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO 此处只是模拟实现权限控制，考虑用Shiro或者Spring Security控制为好，不过没时间写了
//偷懒方法:将管理员和普通用户的页面和接口都分开用拦截器做各自的拦截
@Controller
@RequestMapping("admin/user")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;


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
}
