/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avalon.sword.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jerryran
 */
@Controller
@RequestMapping("/")
public class MediatorController {

    @RequestMapping
    public String getHomePage() {
        return "home";
    }

    @RequestMapping(value = "/user")
    public String getUserPage() {
        return "user";
    }

    @RequestMapping(value = "/admin")
    public String getAdminPage() {
        return "admin";
    }
}
