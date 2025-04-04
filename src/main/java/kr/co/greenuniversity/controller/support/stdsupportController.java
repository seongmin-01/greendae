package kr.co.greenuniversity.controller.support;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class stdsupportController {

    @GetMapping("/support/CourseRegister")
    public String CourseRegister(Authentication auth) {

        return "/Support/CourseRegister";
    }
}
