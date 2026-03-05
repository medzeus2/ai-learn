package com.example.personmaintenance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/persons.html";
    }

    @GetMapping("/persons")
    public String personsRoute() {
        return "redirect:/persons.html";
    }
}
