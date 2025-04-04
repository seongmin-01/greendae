package kr.co.greenuniversity.repository;

import kr.co.greenuniversity.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    List<Department> findByCollege_CollegeName(String collegeName);

    @Query("SELECT MAX(d.no) FROM Department d")
    Optional<Integer> findMaxNo();

    String departmentName(String departmentName);

    Optional<Department> findByDepartmentName(String departmentName);
    Page<Department> findByNo(int no, Pageable pageable);
    Page<Department> findByCollege_CollegeNameContaining(String keyword, Pageable pageable);
    Page<Department> findByDepartmentNameContaining(String keyword, Pageable pageable);
    Page<Department> findByProNameContaining(String keyword, Pageable pageable);
    Page<Department> findByOfficeContaining(String keyword, Pageable pageable);

}