package kr.co.greenuniversity.dto;

import kr.co.greenuniversity.entity.College;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO {

    private int no;
    private String collegeName;
    private String departmentName;
    private String department_eng_name;
    private String establishment_date;
    private String pro_name;
    private String dep_number;
    private String office;
    private int lecture_code;
    private int Student_id;
    private int Professor_id;


}
