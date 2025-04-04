package kr.co.greenuniversity.controller;

import kr.co.greenuniversity.entity.Bachelor.Calender;
import kr.co.greenuniversity.service.Bachelor.CalenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bachelor/schedule")
public class BachelorCaleController {

    private final CalenderService calenderService;

    // ✅ 특정 년/월의 학사 일정 반환 (JSON)
    @GetMapping("/{year}/{month}")
    public List<Calender> getSchedule(@PathVariable int year, @PathVariable int month) {
        log.info("Fetching schedule for year: {}, month: {}", year, month);
        return calenderService.getMonthlySchedule(year, month);
    }
}
