package kr.co.greenuniversity.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.greenuniversity.dto.CollegeDTO;
import kr.co.greenuniversity.entity.College;
import kr.co.greenuniversity.repository.CollegeRepository;

import kr.co.greenuniversity.service.admin.CollegeService;
import kr.co.greenuniversity.service.admin.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CollegeController {

    private final CollegeService collegeService;
    private final DepartmentService departmentService;
    private final CollegeRepository collegeRepository;



    @PostMapping("/Management/registerCollege")
    public String registerCollege(CollegeDTO collegeDTO) {
        MultipartFile file = collegeDTO.getFile();

        if (file != null && !file.isEmpty()) {
            // 원본 파일명 추출 (확장자 포함)
            String originalFilename = file.getOriginalFilename();
            log.info("업로드된 파일명: {}", originalFilename);
            // DTO의 파일명 필드에 저장 (예: fileName)
            collegeDTO.setFileName(originalFilename);

            try {
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // 파일 저장: 파일명에 확장자가 포함되어 있어야 함
                file.transferTo(new File(uploadDir + originalFilename));
            } catch (Exception e) {
                log.error("파일 업로드 실패", e);
            }
        } else {
            log.warn("업로드된 파일이 없습니다.");
            collegeDTO.setFileName(null);
        }

        collegeService.registerCollege(collegeDTO);
        log.info("collegeDTO: {}", collegeDTO);
        return "redirect:/Management/ManageDepartRegist";
    }



}