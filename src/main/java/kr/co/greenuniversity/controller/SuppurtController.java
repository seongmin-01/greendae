package kr.co.greenuniversity.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SuppurtController {

    @GetMapping("/Support/CourseRegister")
    public String CourseRegister() {
        return "/Support/CourseRegister";
    }
    @GetMapping("/Support/GradeList")
    public String GradeList() {
        return "/Support/GradeList";
    }
    @GetMapping("/Support/academicList")
    public String academicList() {
        return "/Support/academicList";
    }
    @GetMapping("/Support/courseList")
    public String courseList() {
        return "/Support/courseList";
    }
    @GetMapping("/Support/curriculumList")
    public String curriculumList() {
        return "/Support/curriculumList";
    }
}
