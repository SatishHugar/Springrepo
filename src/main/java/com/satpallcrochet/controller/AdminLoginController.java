package com.satpallcrochet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class AdminLoginController {

    @GetMapping("/login")
    public String login() {
        log.info("Accessing login page");
        return "admin/login";
    }

    @GetMapping("/logout")
    public String logout() {
        log.info("Logging out");
        return "redirect:/";
    }

}
