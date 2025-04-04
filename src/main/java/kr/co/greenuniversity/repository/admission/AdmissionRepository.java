package kr.co.greenuniversity.repository.admission;

import kr.co.greenuniversity.entity.admission.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdmissionRepository extends JpaRepository<Notice, Integer> {

    @Query(
            "SELECT n " +
            "FROM Notice n"
    )
    List<Notice> SELECTALLLIST();
}
