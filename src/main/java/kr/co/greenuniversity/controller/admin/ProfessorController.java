package kr.co.greenuniversity.controller.admin;

import kr.co.greenuniversity.dto.StudentDTO;
import kr.co.greenuniversity.entity.College;
import kr.co.greenuniversity.entity.Department;
import kr.co.greenuniversity.entity.Professor;
import kr.co.greenuniversity.repository.CollegeRepository;
import kr.co.greenuniversity.repository.DepartmentRepository;
import kr.co.greenuniversity.service.admin.DepartmentService;
import kr.co.greenuniversity.service.admin.ProfessorService;
import kr.co.greenuniversity.service.admission.AdmissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProfessorController {

    private final ProfessorService professorService;
    private final DepartmentService departmentService;
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;

    @GetMapping("/Management/ManageProfessor")
    public String showProfessorForm(Model model) {
        /*
        List<College> colleges = collegeRepository.findAll();
        List<Department> departments = departmentRepository.findAll();

        // 기본 학과 선택해서 ID 예시용으로 하나 뽑기 (또는 아무 학과 없으면 "0000")
        String sampleDepartmentCode = departments.isEmpty() ? "00" : departments.get(0).getDep_number().substring(0, 2);
        String prefix = LocalDate.now().getYear() + sampleDepartmentCode;

        String sampleId = prefix + String.format("%03d", 1);  // 예: 202504001

        model.addAttribute("colleges", colleges);
        model.addAttribute("departments", departments);
        model.addAttribute("generatedId", sampleId); // 👉 미리 보여주기용 id

        return "/Management/ManageProfessor";

         */
        List<College> colleges = collegeRepository.findAll();
        model.addAttribute("colleges", colleges);

        // 기본 선택 학과로 ID 미리 생성
        if (!colleges.isEmpty() && !colleges.get(0).getDepartments().isEmpty()) {
            Department defaultDept = colleges.get(0).getDepartments().get(0);
            String generatedId = professorService.generateProfessorId(defaultDept);
            model.addAttribute("generatedId", generatedId); // <-- View에 전달
        }

        return "/Management/ManageProfessor";
    }


    @PostMapping("/Management/ManageProfessor")
    public String registerProfessor(@ModelAttribute Professor professor,
                                    @RequestParam String departmentName) {
        professorService.registerProfessor(professor, departmentName);
        return "redirect:/Management/ManageProfessorList";
    }

    @GetMapping("/Management/ManageProfessorList")
    public String showProfessorList(Model model, @RequestParam(required = false) String keyword
    , @RequestParam(required = false) String condition, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        //List<Professor> professors = professorService.findAllProfessors();

        List<Professor> professors;
        Page<Professor> page;

        if (keyword != null && condition != null && !keyword.isEmpty() && !condition.isEmpty()) {
            page = professorService.searchProfessor(keyword, condition, pageable);
        }else {
            page = professorService.findAllProfessors(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("condition", condition);

        model.addAttribute("professors", page);
        return "/Management/ManageProfessorList";
    }






}
