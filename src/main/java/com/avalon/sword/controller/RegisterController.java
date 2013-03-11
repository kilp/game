/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avalon.sword.controller;

import com.avalon.sword.pojo.CustomUserDetails;
import com.avalon.sword.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author jerryran
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController extends SimpleFormController {

    private Logger logger=Logger.getLogger(this.getClass().getName());
    private String viewPage;
    
    @Autowired private UserService userService;
    
    public RegisterController() {
        setCommandClass(CustomUserDetails.class);
        setCommandName("registerForm");
    }

    //重写onSubmit方法
    @Override
    public ModelAndView onSubmit(Object command) throws Exception{
        CustomUserDetails user=(CustomUserDetails)command;
        Map model=new HashMap();
        //new String...是用来解决中文乱码问题
//        model.put("yourWords", new String(helloWorld.getMsg().getBytes("iso-8859-1"),"utf-8"));
        logger.log(Level.INFO, "Register {0}", user.toString());
        userService.saveUser(user);
        //返回依赖注入定义的页面
        return new ModelAndView("index",model);
    }
}
