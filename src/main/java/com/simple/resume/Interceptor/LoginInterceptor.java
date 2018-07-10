package com.simple.resume.Interceptor;

import com.simple.resume.common.JwtUtils;
import com.simple.resume.pojo.User;
import com.simple.resume.service.UserService;
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

    @Autowired
    UserService userService;

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
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userToken")) {
                    String token = cookie.getValue();
                    Claims claims = jwtUtils.parseJWT(token);
                    if (claims != null) {
                        Integer userID = (Integer) claims.get("userID");
                        String userName = (String) claims.get("userName");
                        User user = new User();
                        user.setUserID(userID);
                        user.setIsLogined(1);
                        userService.updateUser(user);
                        request.getSession().setAttribute("userID", userID);
                        break;
                    }
                }
            }
        }

        // 获取请求的URL
        String url = request.getRequestURI();

        // 获取Session
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");
        if (userID != null) {
            return true;
        }
        // 不符合条件的，跳转到登录界面
        response.sendRedirect("/login.html");
        return false;
    }
}



