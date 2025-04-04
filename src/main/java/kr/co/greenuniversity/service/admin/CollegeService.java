package kr.co.greenuniversity.service.admin;

import kr.co.greenuniversity.dto.CollegeDTO;
import kr.co.greenuniversity.entity.College;
import kr.co.greenuniversity.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CollegeService {

    private final CollegeRepository collegeRepository;
    private final ModelMapper modelMapper;

    public void registerCollege(CollegeDTO collegeDTO){

      College college = modelMapper.map(collegeDTO, College.class);
        college.setFile(collegeDTO.getFileName());
        log.info("Registering college: {}", college);
        collegeRepository.save(college);

    }
    public List<College> findAll(){
        return collegeRepository.findAll();
    }

    public Optional<College> getCollegeByName(String collegeName) {
        return collegeRepository.findByCollegeName(collegeName);
    }
}
