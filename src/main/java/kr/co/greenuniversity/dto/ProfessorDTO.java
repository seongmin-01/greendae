package kr.co.greenuniversity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorDTO {

    private int id;
    private String jumin;
    private String name;
    private String eng_name;
    private String gender;
    private String phone;
    private String nationality;
    private String email;
    private String addr1;
    private String addr2;
    private String gradColName;
    private String major;
    private String end_date;
    private String degree;
    private String spot;
    private String college_name;
    private String department_name;
    private String appointment_date;
    private String empoly_status;

}
