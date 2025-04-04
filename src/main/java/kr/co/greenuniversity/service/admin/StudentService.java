package kr.co.greenuniversity.service.admin;

import com.querydsl.core.Tuple;
import kr.co.greenuniversity.dto.StudentDTO;
import kr.co.greenuniversity.dto.page.PageRequestDTO;
import kr.co.greenuniversity.dto.page.PageResponseDTO;
import kr.co.greenuniversity.entity.Department;
import kr.co.greenuniversity.entity.Student;
import kr.co.greenuniversity.repository.DepartmentRepository;
import kr.co.greenuniversity.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final DepartmentRepository departmentRepository;

    public List<StudentDTO> StdfindAll() {

        return studentRepository.findAll().stream()
                .map(student -> modelMapper.map(student, StudentDTO.class)) // 변환
                .collect(Collectors.toList()); // 리스트로 변환
    }

    public void registerStudent(Student student, String departmentName) {

        Department department = departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        String generatedId = generateStudentId(department);
        student.setId(generatedId);

        if (!studentRepository.existsById(generatedId)) {
            studentRepository.save(student);  // insert 처리
        } else {
            throw new IllegalStateException("이미 존재하는 학번입니다: " + generatedId);
        }
    }

    public String generateStudentId(Department department) {

        String year = String.valueOf(LocalDate.now().getYear());
        String deptNo = String.format("%02d", department.getNo());
        String prefix = year + deptNo;

        String lastId = studentRepository.findLastIdStartingWith(prefix);

        int nextSeq;
        if (lastId == null) {
            nextSeq = 1;
        } else {
            // 🔥 여기에서 정확하게 자르기 (prefix 길이만큼)
            String seqStr = lastId.substring(prefix.length()); // "01"
            nextSeq = Integer.parseInt(seqStr) + 1;
        }

        // 🔐 최종 학번: "20251202"
        return prefix + String.format("%02d", nextSeq);

    }

    public String generateStudentIdPublic(String departmentName) {

        Department dept = departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new IllegalArgumentException("학과 없음"));
        return generateStudentId(dept);
    }

    public List<StudentDTO> searchStd(String keyword, String condition) {

        List<Student> students;  // 엔티티 리스트 저장할 변수

        switch (condition) {
            case "id":
                students = studentRepository.findByIdContaining(keyword);
                break;
            case "name":
                students = studentRepository.findByNameContaining(keyword);
                break;
            case "jumin":
                students = studentRepository.findByJuminContaining(keyword);
                break;
            case "phone":
                students = studentRepository.findByPhoneContaining(keyword);
                break;
            case "email":
                students = studentRepository.findByEmailContaining(keyword);
                break;
            case "departmentName":
                students = studentRepository.findByDepartment_DepartmentNameContaining(keyword);
                break;
            case "regGrade":
                students = studentRepository.findByRegGradeContaining(keyword);
                break;
            case "regTerm":
                students = studentRepository.findByRegTermContaining(keyword);
                break;
            case "status":
                students = studentRepository.findByStatusContaining(keyword);
                break;
            default:
                students = studentRepository.findAll();
        }

        // **엔티티 → DTO 변환**
        return students.stream()
                .map(this::convertToDTO)  // 변환 메서드 사용
                .collect(Collectors.toList());
        }

    private StudentDTO convertToDTO(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .jumin(student.getJumin())
                .phone(student.getPhone())
                .email(student.getEmail())
                .departmentName(student.getDepartmentName())
                .regGrade(student.getRegGrade())
                .regTerm(student.getRegTerm())
                .status(student.getStatus())
                .build();
    }

}
