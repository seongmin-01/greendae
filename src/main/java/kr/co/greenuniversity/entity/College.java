package kr.co.greenuniversity.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(exclude = "departments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "College")
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(name = "college_name", unique = true)
    private String collegeName;

    private String college_eng_name;
    private String info_title;
    private String info_context;
    private String file;

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Department> departments;




}
