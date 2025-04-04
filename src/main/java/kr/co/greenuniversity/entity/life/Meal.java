package kr.co.greenuniversity.entity.life;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Meal")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(name = "meal_date")
    private LocalDate mealDate;

    private String morning;

    @Column(name = "mo_price")
    private Integer moPrice;


    private String lunch;

    @Column(name = "lu_price")
    private Integer luPrice;

    private String dinner;

    @Column(name = "di_price")
    private Integer diPrice;
}