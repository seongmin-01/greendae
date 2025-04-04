package kr.co.greenuniversity.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {

    private String id;
    private String name;
    private String jumin;
    private String eng_name;
    private String gender;
    private String nationality;
    private String phone;
    private String email;
    private String addr1;
    private String addr2;
    private String reg_year;
    private String classification;
    private String end_year;
    private String college_name;
    @Column(name = "department_name")
    private String departmentName;
    private String pro_name;
    @Column(name = "reg_grade")
    private String regGrade;
    @Column(name = "reg_term")
    private String regTerm;
    private String status;


}
