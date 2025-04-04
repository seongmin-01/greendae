package kr.co.greenuniversity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value= {"/", "main"})
    public String MainPage(Authentication auth) {

        return "/main";
    }

    @GetMapping("/adminMain")
    public String AdminPage(Authentication auth) {

        return "/Management/Managemain";
    }
}
