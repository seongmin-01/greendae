package kr.co.greenuniversity.repository;

import kr.co.greenuniversity.entity.Department;
import kr.co.greenuniversity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findStudentById(String id);

    List<Student> findByIdStartingWith(String idPrefix);

    long countByIdStartingWith(String prefix);

    int countByDepartment(Department department);

    @Query("SELECT MAX(s.id) FROM Student s WHERE s.id LIKE CONCAT(:prefix, '%')")
    String findLastIdStartingWith(@Param("prefix") String prefix);


    // 검색
    List<Student> findByIdContaining(String id);
    List<Student> findByNameContaining(String name);
    List<Student> findByJuminContaining(String jumin);
    List<Student> findByPhoneContaining(String phone);
    List<Student> findByEmailContaining(String email);
    List<Student> findByDepartment_DepartmentNameContaining(String departmentName);
    List<Student> findByRegGradeContaining(String regGrade);
    List<Student> findByRegTermContaining(String regTerm);
    List<Student> findByStatusContaining(String status);

}
