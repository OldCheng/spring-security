package com.springsecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author 程杰
 * @Date 2021/4/18 16:08
 * @Version 1.0
 */
@Controller
public class LoginController {

//    @PostMapping(value = "/login")
//    public String login(){
//        return "redirect:main.html";
//    }

//    @Secured("ROLE_abc")
    //PreAuthorize允许角色以ROLE_开头也可以不以ROLE_开头，但是配置类不能以ROLE_开头
    @PreAuthorize("hasRole('abc')")
    @RequestMapping(value = "/toMain")
    public String main(){
        return "redirect:main.html";
    }

    @RequestMapping(value = "/toError")
    public String error(){
        return "redirect:error.html";
    }

    @ResponseBody
    @RequestMapping(value = "/demo")
    public String demo(){
        return "demo";
    }
}
