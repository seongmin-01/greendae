package kr.co.greenuniversity.controller;

import kr.co.greenuniversity.service.life.MealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class lifeController {

    private final MealService mealService;

    @GetMapping("/life/council")
    public String council() {
        return "/life/council";
    }
    @GetMapping("/life/galary")
    public String galary() {
        return "/life/galary";
    }
    @GetMapping("/life/meal")
    public String meal(@RequestParam(required = false) String startDateStr, Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        try {
            if (startDateStr == null) {
                // 기본 시작 날짜 (예: 2025-03-10)
                startDate = sdf.parse("2025-03-10");
            } else {
                startDate = sdf.parse(startDateStr);
            }
        } catch (ParseException e) {
            startDate = new Date();
        }
        // 종료 날짜: 시작일로부터 6일 뒤 (일주일 범위)
        Date endDate = new Date(startDate.getTime() + 6L * 24 * 60 * 60 * 1000);

        List<?> meals = mealService.getMealsBetween(startDate, endDate);
        model.addAttribute("meals", meals);
        return "/life/meal";  // Thymeleaf 템플릿 경로
    }
    @GetMapping("/life/study")
    public String study() {
        return "/life/study";
    }


}
