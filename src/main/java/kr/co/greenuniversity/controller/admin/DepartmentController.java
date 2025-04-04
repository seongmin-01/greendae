package kr.co.greenuniversity.controller.admin;

import kr.co.greenuniversity.dto.DepartmentDTO;
import kr.co.greenuniversity.entity.College;
import kr.co.greenuniversity.entity.Department;
import kr.co.greenuniversity.entity.Professor;
import kr.co.greenuniversity.repository.CollegeRepository;
import kr.co.greenuniversity.service.admin.CollegeService;
import kr.co.greenuniversity.service.admin.DepartmentService;
import kr.co.greenuniversity.service.admin.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class DepartmentController {

    private final DepartmentService departmentService;
    private final CollegeRepository collegeRepository;
    private final ProfessorService professorService;
    private final CollegeService collegeService;

    @PostMapping("/Management/registerDepart")
    public String registerDepartment(@ModelAttribute DepartmentDTO departmentDTO) {

        departmentService.registerDepartment(departmentDTO);
        return "redirect:/Management/ManageDepartRegist";
    }

    @GetMapping("/Management/ManageDepartRegist")
    public String showDepartmentForm(Model model, Pageable pageable) {

        Page<Professor> professors = professorService.findAllProfessors(pageable);
        model.addAttribute("professors", professors);

        int nextNo = departmentService.getNextDepartmentNo();

        if (nextNo < 10) nextNo = 10;
        String formattedNo = String.format("%02d", nextNo);

        model.addAttribute("nextNo", formattedNo);
        model.addAttribute("colleges", collegeRepository.findAll());

        return "/Management/ManageDepartRegist";
    }

    @GetMapping("/department/engineering")
    public String engineering(Model model) {
        College college = collegeService.getCollegeByName("공과대학")
                .orElse(new College());
        List<Department> departments = departmentService.getDepartmentsByCollegeName("공과대학");
        model.addAttribute("college", college);
        model.addAttribute("departments", departments);
        return "/department/engineering";
    }

    @GetMapping("/department/naturalscience")
    public String naturalscience(Model model) {
        College college = collegeService.getCollegeByName("자연과학대학")
                .orElse(new College());
        List<Department> departments = departmentService.getDepartmentsByCollegeName("자연과학대학");
        model.addAttribute("college", college);
        model.addAttribute("departments", departments);
        return "/department/naturalscience"; // templates/department/naturalscience.html
    }

    @GetMapping("/department/humanities")
    public String humanities(Model model) {
        College college = collegeService.getCollegeByName("인문사회대학")
                .orElse(new College());
        List<Department> departments = departmentService.getDepartmentsByCollegeName("인문사회대학");
        model.addAttribute("college", college);
        model.addAttribute("departments", departments);
        return "/department/humanities"; // templates/department/humanities.html
    }

    @GetMapping("/department/teacher")
    public String teacher(Model model) {
        College college = collegeService.getCollegeByName("사범대학")
                .orElse(new College());
        List<Department> departments = departmentService.getDepartmentsByCollegeName("사범대학");
        model.addAttribute("college", college);
        model.addAttribute("departments", departments);
        return "/department/teacher"; // templates/department/teacher.html
    }

    @GetMapping("/department/graduateSchool")
    public String graduateSchool(Model model) {
        College college = collegeService.getCollegeByName("대학원")
                .orElse(new College());
        List<Department> departments = departmentService.getDepartmentsByCollegeName("대학원");
        model.addAttribute("college", college);
        model.addAttribute("departments", departments);
        return "/department/graduateSchool"; // templates/department/graduateSchool.html
    }

    @GetMapping("/Management/ManagerDptList")
    public String listview(Model model, @RequestParam(required = false) String condition,
                           @RequestParam(required = false) String keyword,
                           @RequestParam(defaultValue = "0")int page,
                           @RequestParam(defaultValue = "10")int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Department> departmentPage;

        if (keyword != null && condition != null && !condition.isEmpty() && !keyword.isEmpty()) {
            departmentPage = departmentService.findAllDepartment(pageable);
        }else {
            departmentPage = departmentService.searchDepartment(condition, keyword, pageable);
        }

        model.addAttribute("departments", departmentPage.getContent());
        model.addAttribute("condition", condition);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", departmentPage);

        return "/Management/ManagerDptList";
    }

}