package kr.co.greenuniversity.service.admission;

import jakarta.persistence.EntityNotFoundException;
import kr.co.greenuniversity.dto.admission.NoticeDTO;
import kr.co.greenuniversity.entity.admission.Notice;
import kr.co.greenuniversity.repository.admission.AdmissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdmissionService {

    private final AdmissionRepository admissionRepository;

    // 🔹 공지사항 목록 조회
    public List<NoticeDTO> findAll() {
        List<Notice> noticeList = admissionRepository.SELECTALLLIST();

        return noticeList.stream()
                .map(Notice::toDTO)  // Notice 엔티티를 NoticeDTO로 변환
                .collect(Collectors.toList());
    }

    // 🔹 공지사항 상세 조회 (추가됨)
    public NoticeDTO findById(int no) {
        Optional<Notice> optNotice = admissionRepository.findById(no);

        if (optNotice.isPresent()) {
            Notice notice = optNotice.get();
            return notice.toDTO();
        }
        return null;
    }

    public void delete(int no) {
        admissionRepository.deleteById(no);
    }
}

