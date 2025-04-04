package kr.co.greenuniversity.repository.Bachelor;

import kr.co.greenuniversity.entity.Bachelor.Calender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CalenderRepository extends JpaRepository<Calender, Long> {
    @Query("SELECT c FROM Calender c WHERE YEAR(c.eventDate) = :year AND MONTH(c.eventDate) = :month")
    List<Calender> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
}

