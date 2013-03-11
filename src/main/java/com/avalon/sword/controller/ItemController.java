/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avalon.sword.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jerryran
 */
@Controller
@RequestMapping(value = "/item")
public class ItemController {
    
    @RequestMapping(value = "get")
    @ResponseBody
    String get(@RequestParam("itemid") int itemid)
    {
        return "/item/get/itemid=" + itemid;
    }
    
    @RequestMapping(value = "getall")
    @ResponseBody
    String getAll(@RequestParam("uid") int uid, int start, int end)
    {
        return "/item/getall?uid=" + uid + "&start=" + start + "&end=" + end;
    }
}
