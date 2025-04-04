package kr.co.greenuniversity.service.admin;

import kr.co.greenuniversity.entity.Department;
import kr.co.greenuniversity.entity.Lecture;
import kr.co.greenuniversity.repository.DepartmentRepository;
import kr.co.greenuniversity.repository.LectureRepository;
import kr.co.greenuniversity.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class LectureService {

    private final ProfessorRepository professorRepository;
    private final ModelMapper modelMapper;
    private final DepartmentRepository departmentRepository;
    private final LectureRepository lectureRepository;

    public void registerLecture(Lecture lecture, String departmentName) {

        Department dept = departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        String generatedCode = generateLectureCode(dept, lecture.getStartDate());
        lecture.setCode(generatedCode);
        lecture.setDepNo(String.format("%02d", dept.getNo()));
        lecture.setColName(dept.getCollege().getCollegeName());
        lecture.setDepartmentName(dept.getDepartmentName());

        lectureRepository.save(lecture);
    }

    public String generateLectureCode(Department dept, LocalDate startDate) {
        String depNo = String.format("%02d", dept.getNo());
        String year = String.valueOf(startDate.getYear()); // ✅ 파라미터에서 연도 추출
        int semester = (startDate.getMonthValue() <= 6) ? 1 : 2; // ✅ 1학기 or 2학기

        int count = lectureRepository.countByDepNoAndStartDate(depNo, startDate); // ✅ LocalDate 타입 일치

        String seq = String.format("%02d", count + 1);

        return depNo + year + semester + seq;
    }




}
