package kr.co.greenuniversity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employ_ArticleDTO {

    private int no;
    private String situation;
    private String company;
    private String employInfo;
    private String deadline;
    private int check;
    private String regip;


}
