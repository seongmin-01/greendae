package kr.co.greenuniversity.controller;

import kr.co.greenuniversity.entity.Bachelor.Calender;
import kr.co.greenuniversity.service.Bachelor.CalenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/Bachelor")
public class BachelorController {

    // ✅ 학사 일정 페이지 반환 (HTML View)
    @GetMapping("/BacGrad")
    public String BacGrad() {
        return "/Bachelor/BacGrad";
    }

    @GetMapping("/BacNotice")
    public String BacNotice() {
        return "/Bachelor/BacNotice";
    }

    @GetMapping("/BacQuestion")
    public String BacQuestion() {
        return "/Bachelor/BacQuestion";
    }

    @GetMapping("/BacResult")
    public String BacResult() {
        return "/Bachelor/BacResult";
    }

    @GetMapping("/BacSchedule")
    public String BacSchedule() {
        return "/Bachelor/BacSchedule";
    }

    @GetMapping("/BacSubscribe")
    public String BacSubscribe() {
        return "/Bachelor/BacSubscribe";
    }
}
