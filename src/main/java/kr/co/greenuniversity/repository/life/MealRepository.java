package kr.co.greenuniversity.repository.life;


import kr.co.greenuniversity.entity.life.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Integer> {
    List<Meal> findByMealDateBetween(LocalDate startDate, LocalDate endDate);
}
