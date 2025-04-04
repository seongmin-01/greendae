package kr.co.greenuniversity.service.Bachelor;

import kr.co.greenuniversity.entity.Bachelor.Calender;
import kr.co.greenuniversity.repository.Bachelor.CalenderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalenderService {

    private final CalenderRepository calenderRepository;

    public List<Calender> getMonthlySchedule(int year, int month) {
        return calenderRepository.findByYearAndMonth(year, month);
    }


}
