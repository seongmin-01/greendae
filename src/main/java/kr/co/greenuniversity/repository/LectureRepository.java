package kr.co.greenuniversity.repository;


import kr.co.greenuniversity.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    int countByDepNoAndStartDate(String depNo, LocalDate startDate);

}
