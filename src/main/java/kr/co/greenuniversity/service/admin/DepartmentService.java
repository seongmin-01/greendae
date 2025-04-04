package kr.co.greenuniversity.service.admin;

import kr.co.greenuniversity.dto.DepartmentDTO;
import kr.co.greenuniversity.entity.College;
import kr.co.greenuniversity.entity.Department;
import kr.co.greenuniversity.entity.Professor;
import kr.co.greenuniversity.repository.CollegeRepository;
import kr.co.greenuniversity.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final CollegeRepository collegeRepository;
    private final ModelMapper modelMapper;

    public void registerDepartment(DepartmentDTO departmentDTO) {

        Department department = modelMapper.map(departmentDTO, Department.class);
        log.info("Registering department {}", department);

        College college = collegeRepository.findByCollegeName(departmentDTO.getCollegeName())
                .orElseThrow(() -> new IllegalArgumentException("대학명이 존재하지 않습니다."));

        department.setCollege(college);
        departmentRepository.save(department);
        log.info("Department {}", department);
    }

    public List<Department> getDepartmentsByCollegeName(String collegeName){

        return departmentRepository.findByCollege_CollegeName(collegeName);

    }

    public Page<Department> findAllDepartment(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }
    public Page<Department> searchDepartment(String condition, String keyword, Pageable pageable) {

        if (condition == null || condition.isBlank() || keyword == null || keyword.isBlank()) {
            return departmentRepository.findAll(pageable);
        }

        switch (condition) {
            case "no":
                try {
                    int no = Integer.parseInt(keyword);
                    return departmentRepository.findByNo(no, pageable);
                } catch (NumberFormatException e) {
                    return Page.empty();
                }
            case "college_name":
                return departmentRepository.findByCollege_CollegeNameContaining(keyword, pageable);
            case "departmentName":
                return departmentRepository.findByDepartmentNameContaining(keyword, pageable);
            case "proName":
                return departmentRepository.findByProNameContaining(keyword, pageable);
            case "dep_number":
                return departmentRepository.findByOfficeContaining(keyword, pageable);
            default:
                return departmentRepository.findAll(pageable);
        }

    }

    public int getNextDepartmentNo() {
        Optional<Integer> maxNo = departmentRepository.findMaxNo();
        return (maxNo.orElse(0) + 1) % 100;
    }

}