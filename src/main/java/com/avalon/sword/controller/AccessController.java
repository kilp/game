/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avalon.sword.controller;

import com.avalon.sword.pojo.CustomUserDetails;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jerryran
 */
@Controller
@RequestMapping
public class AccessController {
    private static final Logger logger = Logger.getLogger(AccessController.class.getName());
    
    @RequestMapping("/login")
    public String login(Model model, @RequestParam(required = false) String message) {
        logger.log(Level.INFO, "Login parms = {0}", model);
        model.addAttribute("message", message);
        return "access/login";
    }

    @RequestMapping(value = "/denied")
    public String denied() {
        logger.log(Level.INFO, "Login denied");
        return "access/denied";
    }

    @RequestMapping(value = "/login/failure")
    public String loginFailure() {
        String message = "Login Failure!";
        return "redirect:/login?message=" + message;
    }

    @RequestMapping(value = "/logout/success")
    public String logoutSuccess() {
        String message = "Logout Success!";
        return "redirect:/login?message=" + message;
    }
}
