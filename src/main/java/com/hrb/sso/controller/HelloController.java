package com.hrb.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "/")
public class HelloController {

    @RequestMapping(value = "sayHi")
    @ResponseBody
    public String sayHi(){
        return "hello world";
    }

}
