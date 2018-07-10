package com.simple.resume.listener;

import com.simple.resume.pojo.User;
import com.simple.resume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Autowired
    UserService userService;

    @Override

    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session建立");
        //设置当前会话的有效时间为30min
        se.getSession().setMaxInactiveInterval(30 * 60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session销毁");
        User user = new User();
        Integer userID = (Integer)se.getSession().getAttribute("userID");
        user.setUserID(userID);
        user.setIsLogined(0);
        userService.updateUser(user);
        se.getSession().invalidate();
    }
}
