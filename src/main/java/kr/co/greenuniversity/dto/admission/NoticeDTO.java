package kr.co.greenuniversity.dto.admission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDTO {
    private int no;
    private String cate;
    private String title;
    private String content;
    private String comment;
    private String file;
    private String hit;
    private String writer;
    private String regip;
    private String wdate;




}
