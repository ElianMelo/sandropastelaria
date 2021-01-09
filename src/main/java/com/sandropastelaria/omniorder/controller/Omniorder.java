package com.sandropastelaria.omniorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Omniorder {

    @RequestMapping("/")
    public String controllerLogin() {
        return "login";
    }
    
}
