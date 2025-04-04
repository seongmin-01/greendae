package kr.co.greenuniversity.repository;

import kr.co.greenuniversity.entity.Department;
import kr.co.greenuniversity.entity.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, String> {

    // 교수 ID로 검색 (기본 제공됨)
    Optional<Professor> findById(String id);

    List<Professor> findByIdStartingWith(String idPrefix);


    long countByIdStartingWith(String prefix);

    int countByDepartment(Department department);

    Page<Professor> findByIdContaining(String id, Pageable pageable);
    Page<Professor> findByNameContaining(String name, Pageable pageable);
    Page<Professor> findByJuminContaining(String jumin, Pageable pageable);
    Page<Professor> findByPhoneContaining(String phone, Pageable pageable);
    Page<Professor> findByEmailContaining(String email, Pageable pageable);
    Page<Professor> findByDepartment_DepartmentNameContaining(String departmentName, Pageable pageable);
    Page<Professor> findByPositionContaining(String position, Pageable pageable);
    Page<Professor> findByEmployStatusContaining(String employStatus, Pageable pageable);
    Page<Professor> findByappointmentDateContaining(String appointmentDate, Pageable pageable);

    @Query("SELECT p FROM Professor p WHERE " +
            "(:condition = 'id' AND p.id LIKE %:keyword%) OR " +
            "(:condition = 'name' AND p.name LIKE %:keyword%) OR " +
            "(:condition = 'email' AND p.email LIKE %:keyword%)")
    Page<Professor> search(@Param("condition") String condition, @Param("keyword") String keyword, Pageable pageable);
}
