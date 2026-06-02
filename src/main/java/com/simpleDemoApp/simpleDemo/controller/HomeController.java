package com.simpleDemoApp.simpleDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController
{
    @RequestMapping("greet")
    public String  greet(){
        return("wow i control everything");
    }
}
