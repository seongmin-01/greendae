package kr.co.greenuniversity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollegeDTO {

    private int no;
    private String collegeName;
    private String college_eng_name;
    private String fileName;
    private String info_title;
    private String info_context;
    private MultipartFile file;


}
