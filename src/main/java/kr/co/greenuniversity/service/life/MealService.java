package kr.co.greenuniversity.service.life;


import kr.co.greenuniversity.entity.life.Meal;
import kr.co.greenuniversity.repository.life.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;

    public List<Meal> getMealsBetween(Date startDate, Date endDate) {
        // java.util.Date를 java.time.LocalDate로 변환
        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return mealRepository.findByMealDateBetween(localStartDate, localEndDate);
    }
}