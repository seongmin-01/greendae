package kr.co.greenuniversity.controller.file;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.greenuniversity.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
이름: 김소현
내용: 커뮤니티1,2 테이블 파일
 */

@Slf4j
@RequiredArgsConstructor
@Controller
public class FileController {

    private final FileService fileService;

    @GetMapping("/file/download")
    public ResponseEntity download(int fno){
        return fileService.downloadFile(fno);
    }
}
