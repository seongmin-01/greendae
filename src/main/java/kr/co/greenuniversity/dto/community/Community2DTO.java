package kr.co.greenuniversity.dto.community;

import kr.co.greenuniversity.dto.file.FileDTO;
import kr.co.greenuniversity.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Community2DTO {
    private int no;
    private String status;
    private String deadline;
    private String type;
    private String cate;
    private String title;
    private String content;
    private int comment;
    private int file;
    private int hit;
    private String writer;
    private String regip;
    private String wdate;
    private Boolean locked;
    private String password;

    // 추가 컬럼
    private String name;
    private User user;
    private List<FileDTO> files;
    private boolean expired;

    public String getDeadline() {
        if (deadline != null && !deadline.isBlank()) {
            return deadline.substring(0, 10);
        }
        return null;
    }

    public String getWdate() {
        if(wdate != null){
            return wdate.substring(0, 10);
        }
        return null;
    }

    private MultipartFile file1;
    private MultipartFile file2;

    public List<MultipartFile> getMultipartFiles(){
        return List.of(file1, file2);
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

}
