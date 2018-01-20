package com.nightingale.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    private final static String FOLDER = "/home";

    @GetMapping("/")
    public String home(Model model) {
        return FOLDER + "/home";
    }

}
