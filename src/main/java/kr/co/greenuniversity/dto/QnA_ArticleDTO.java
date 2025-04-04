package kr.co.greenuniversity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnA_ArticleDTO {

    private int no;
    private String title;
    private String content;
    private String writer;
    private String wdate;
    private String situation;
    private String regip;


}
