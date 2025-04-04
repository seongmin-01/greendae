package kr.co.greenuniversity.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "User")
public class User {

    @Id
    private String id;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String addr1;
    private String addr2;


    @Column(nullable = false)
    private String role;


    @PrePersist
    public void prePersist(){
        if(this.role == null){
            this.role = "USER";
        }
    }

    private String regip;

    @CreationTimestamp
    private LocalDateTime regDate;

    private String leaveDate;

}