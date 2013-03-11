/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avalon.sword.controller;

import com.avalon.sword.model.Person;
import com.avalon.sword.pojo.CustomUserDetails;
import com.avalon.sword.pojo.User;
import com.avalon.sword.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jerryran
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class.getName());
    
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "/get")
    @ResponseBody
    public String get(@RequestParam("uid") int uid)
    {
        return "Success";
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(@RequestParam("uid") int uid)
    {
        return "Success";
    }
    
    @RequestMapping(value = "/create")
    @ResponseBody
    public String create(CustomUserDetails user)
    {
        logger.log(Level.INFO, "create: {0}", user);
        userService.saveUser(user);
        return "Success";
    }
    
    @RequestMapping(value = "/find")
    @ResponseBody
    public User find(@RequestParam("name") String name)
    {
        logger.log(Level.INFO, "find: {0}", name);
        return userService.findUserByName(name);
//        return "hello";
    }
    
    @RequestMapping(value = "/test")
    @ResponseBody
    public Person test()
    {
        Person person = new Person("test", 10);
        return person;
    }
}
