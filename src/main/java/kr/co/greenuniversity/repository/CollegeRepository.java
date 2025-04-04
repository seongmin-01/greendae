package kr.co.greenuniversity.repository;

import kr.co.greenuniversity.entity.College;
import kr.co.greenuniversity.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollegeRepository extends JpaRepository<College, Integer> {
    Optional<College> findByCollegeName(String collegeName);
    List<Professor> findByDepartments_College_CollegeNameAndDepartments_DepartmentName(String collegeName, String departmentName);

}
