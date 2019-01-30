package com.wooread.wooreadpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("register")
    public String register(){
        return "register";
    }

}
