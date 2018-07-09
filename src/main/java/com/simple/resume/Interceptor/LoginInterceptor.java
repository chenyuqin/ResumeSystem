package com.simple.resume.Interceptor;

import com.simple.resume.common.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //先处理cookie,如果cookie中含有remember me的token,则解析后识别为已登录(存入session中)
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.sendRedirect("login.html");
            return false;
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userToken")) {
                    String token = cookie.getValue();
                    Claims claims = jwtUtils.parseJWT(token);
                    if (claims != null) {
                        Integer userID = (Integer) claims.get("userID");
                        String userName = (String) claims.get("userName");
                        request.getSession().setAttribute("userID", userID);
                    }
                }
            }
        }
        // 获取请求的URL
        String url = request.getRequestURI();

        //以下是对不拦截的url做识别并返回true(通过)
        //TODO 此处直接对所有接口做不识别(因为懒和不知道正确的逻辑)
        if (url.indexOf(".html") < 0) {
            return true;
        }
        //对登录和注册的页面做不拦截
        if (url.indexOf("login") >= 0 || url.indexOf("register") >= 0) {
            return true;
        }
        //对激活成功和失败页面做不拦截
        if (url.indexOf("success") >= 0 || url.indexOf("failed") >= 0) {
            return true;
        }
        //对邮箱修改密码的响应页面做不拦截
        if (url.indexOf("alterPassword") >= 0 || url.indexOf("error") >= 0) {
            return true;
        }
//        if (url.indexOf("alterPassword") >= 0 || url.indexOf("failed") >= 0 || url.indexOf("success") >= 0) {
//            return true;
//        }
//
//        // 注意：一些静态文件不能拦截，否则会死循环，知道内存耗尽
//        if (url.indexOf("login") >= 0 || url.indexOf("changePassword") >= 0 || url.indexOf("register") >= 0 || url.indexOf("common") >= 0) {
//            return true;
//        }



        // 获取Session
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");
        if (userID != null) {
            return true;
        }
        // 不符合条件的，跳转到登录界面
        response.sendRedirect("login.html");
        return false;
    }
}



