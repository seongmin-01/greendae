package kr.co.greenuniversity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureDTO {

    private int code;
    private String dep_no;
    private String col_name;
    private String dep_name;
    private String grade;
    private String credit;
    private String division;
    private String pro_name;
    private String lec_name;
    private String lec_info;
    private String start_date;
    private String end_date;
    private String start_time;
    private String end_time;
    private String day;
    private String evaluation;
    private String book;
    private String classroom;
    private String people_num;


}
