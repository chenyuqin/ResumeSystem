package com.simple.resume.controller;

import com.simple.resume.common.JsonResult;
import com.simple.resume.pojo.User;
import com.simple.resume.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String saveUser(User user) {
        userService.saveUser(user);
        JSON json = JSONSerializer.toJSON(new JsonResult());
        return json.toString();
    }

    @RequestMapping("/a")
    @ResponseBody
    public String a(){
        return "Aaaaa";
    }
}
