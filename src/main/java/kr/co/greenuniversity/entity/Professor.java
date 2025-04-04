package kr.co.greenuniversity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Professor")
public class Professor {


    @Id
    private String id;

    private String jumin;

    @Column(name = "name")
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

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "appointment_date")
    private String appointmentDate;

    @Column(name = "employ_status")
    private String employStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    private String position;

    @PrePersist
    public void prePersist(){
        if(this.spot == null){
            this.spot = "정교수";
        }
        if(this.employStatus == null){
            this.employStatus = "재직중";
        }

    }


}
