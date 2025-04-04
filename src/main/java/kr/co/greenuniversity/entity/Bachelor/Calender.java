package kr.co.greenuniversity.entity.Bachelor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Calender")
public class Calender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "eventDate") // DB의 컬럼명과 일치해야 함
    private LocalDate eventDate; // LocalDate or LocalDateTime 등

    @Column(name = "title")
    private String title;


}
