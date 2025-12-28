package com.mycompany.ordersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping(path = "/")
    public String home() {
        return "index";
    }
}
