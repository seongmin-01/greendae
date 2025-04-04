package kr.co.greenuniversity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News_ArticleDTO {

    private int no;
    private String newsDivision;
    private String title;
    private String content;
    private String writer;
    private String regip;
    private String wdate;
    private int check;


}
