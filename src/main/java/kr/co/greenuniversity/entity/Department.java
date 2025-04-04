package kr.co.greenuniversity.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import javax.naming.Name;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"college", "professors", "student", "lecture"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Department")
public class Department {

    @Id
    private int no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_name", referencedColumnName = "college_name")
    private College college;

    @Column(name = "department_name")
    private String departmentName;

    private String department_eng_name;
    private String establishment_date;

    @Column(name = "pro_name")
    private String proName;

    private String dep_number;
    private String office;

    @OneToMany(mappedBy = "department")
    private List<Professor> professors;

    @OneToMany(mappedBy = "department")
    private List<Student> student;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "lecture_code", nullable = true)
    @JsonBackReference
    private Lecture lecture;


}
