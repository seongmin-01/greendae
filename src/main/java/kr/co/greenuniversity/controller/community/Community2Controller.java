package kr.co.greenuniversity.controller.community;


import kr.co.greenuniversity.dto.page.PageRequestDTO;
import kr.co.greenuniversity.dto.page.PageResponseDTO;
import kr.co.greenuniversity.service.community.Community2Service;
import kr.co.greenuniversity.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/*
이름: 김소현
내용: 커뮤니티2 테이블 목록, 검색
 */

@Slf4j
@RequiredArgsConstructor
@Controller
public class Community2Controller {

    private final Community2Service communityService;

    // 목록
    @GetMapping("/Community/job")
    public String job(PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setCate("job");
        PageResponseDTO pageResponseDTO = communityService.findAll(pageRequestDTO);
        model.addAttribute(pageResponseDTO);
        return "/Community/job";
    }

    @GetMapping("/Community/news")
    public String news(PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setCate("news");
        PageResponseDTO pageResponseDTO = communityService.findAll(pageRequestDTO);
        model.addAttribute(pageResponseDTO);
        return "/Community/news";
    }

    @GetMapping("Community/qa")
    public String qa(PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setCate("qa");
        PageResponseDTO pageResponseDTO = communityService.findAll(pageRequestDTO);
        model.addAttribute(pageResponseDTO);
        return "/Community/qa";
    }


    // 검색
    @GetMapping("/Community/searchJob")
    public String searchJob(PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setCate("job");
        PageResponseDTO pageResponseDTO = communityService.searchAll(pageRequestDTO);
        model.addAttribute(pageResponseDTO);
        return "/Community/search/searchJob";
    }

    @GetMapping("/Community/searchNews")
    public String searchNews(PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setCate("news");
        PageResponseDTO pageResponseDTO = communityService.searchAll(pageRequestDTO);
        model.addAttribute(pageResponseDTO);
        return "/Community/search/searchNews";
    }

    @GetMapping("/Community/searchQa")
    public String searchQa(PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setCate("qa");
        PageResponseDTO pageResponseDTO = communityService.searchAll(pageRequestDTO);
        model.addAttribute(pageResponseDTO);
        return "/Community/search/searchQa";
    }

}
