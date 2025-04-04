package kr.co.greenuniversity.controller;

import kr.co.greenuniversity.dto.admission.NoticeDTO;
import kr.co.greenuniversity.service.admission.AdmissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdmissionController {

    private final AdmissionService admissionService;

    @GetMapping("/Admission/admissionCounseling")
    public String admissionCounseling() {
        return "/Admission/admissionCounseling";
    }

    @GetMapping("/Admission/earlyAdmission")
    public String earlyAdmission() {
        return "/Admission/earlyAdmission";
    }

    // 🔹 공지사항 목록 조회
    @GetMapping("/Admission/notice")
    public String notice(Model model) {
        List<NoticeDTO> noticeDTOList = admissionService.findAll();

        log.info("noticeDTOList: {}", noticeDTOList);

        model.addAttribute("noticeDTOList", noticeDTOList); // key 값 지정

        return "/Admission/notice";
    }

    // 🔹 공지사항 상세보기 요청 추가
    @GetMapping("/Admission/view")
    public String noticeDetail(@RequestParam("no") int no, Model model) {
        NoticeDTO noticeDTO = admissionService.findById(no);
        log.info("noticeDTO: {}", noticeDTO);
        model.addAttribute(noticeDTO);

        return "/Admission/view/view"; // 상세보기 페이지
    }


    @GetMapping("/Admission/delete")
    public String deleteAdmission(@RequestParam("no") int no) {
        admissionService.delete(no);
        return "redirect:/Admission/notice";
    }




    @GetMapping("/Admission/regularAdmission")
    public String regularAdmission() {
        return "/Admission/regularAdmission";
    }

    @GetMapping("/Admission/transferAdmission")
    public String transferAdmission() {
        return "/Admission/transferAdmission";
    }
}