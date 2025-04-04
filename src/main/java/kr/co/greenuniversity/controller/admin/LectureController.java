package kr.co.greenuniversity.controller.admin;

import kr.co.greenuniversity.entity.College;
import kr.co.greenuniversity.entity.Lecture;
import kr.co.greenuniversity.entity.Professor;
import kr.co.greenuniversity.repository.CollegeRepository;
import kr.co.greenuniversity.service.admin.LectureService;
import kr.co.greenuniversity.service.admin.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LectureController {

    private final ProfessorService professorService;
    private final CollegeRepository collegeRepository;
    private final LectureService lectureService;

    @GetMapping("/Management/ManageRegister")
    public String view(Model model, Pageable pageable) {

        List<College> colleges = collegeRepository.findAll();
        Page<Professor> professors = professorService.findAllProfessors(pageable);
        model.addAttribute("colleges", colleges);
        model.addAttribute("professors", professors);

        return "/Management/ManageRegister";
    }

    @PostMapping("/Management/ManageRegister")
    public String registerLecture(@ModelAttribute Lecture lecture,
                                  @RequestParam String departmentName) {
            log.info("lecture={}", lecture);
        lectureService.registerLecture(lecture, departmentName);
        return "redirect:/Management/ManageRegister";

    }

    @GetMapping("/Management/ManageInClass")
    public String ManageInClass() {
        return "/Management/ManageInClass";
    }

    @GetMapping("/Management/ManageEducation")
    public String ManageEducation() {
        return "/Management/ManageEducation";
    }

    @GetMapping("/Management/ManageCourseList")
    public String ManageCourseList() {
        return "/Management/ManageCourseList";
    }

}
