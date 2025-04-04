package kr.co.greenuniversity.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IntroductionController {

    @GetMapping("/Introduction/directions")
    public String directions() {
        return "/Introduction/directions";
    }

    @GetMapping("/Introduction/educationalPhilosophy")
    public String educationalPhilosophy() {
        return "/Introduction/educationalPhilosophy";
    }

    @GetMapping("/Introduction/history")
    public String history(){
        return "/Introduction/history";
    }

    @GetMapping("/Introduction/organizationalChart")
    public String organizationalChart(){
        return "/Introduction/organizationalChart";

    }

    @GetMapping("/Introduction/presidentMessage")
    public String presidentMessage(){
        return "/Introduction/presidentMessage";
    }
}
