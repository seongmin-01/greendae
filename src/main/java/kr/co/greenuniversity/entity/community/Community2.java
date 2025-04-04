package kr.co.greenuniversity.entity.community;

import jakarta.persistence.*;
import kr.co.greenuniversity.entity.File.File;
import kr.co.greenuniversity.entity.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Community2")
public class Community2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private String status;
    private String type;
    private String deadline;

    @Column(length = 60)
    private String password;

    @Column(nullable = false)
    private String cate;

    private String title;
    private String content;
    private int comment;
    private int file;
    private int hit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private User user;

    private String regip;

    @CreationTimestamp
    private LocalDateTime wdate;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean locked;

    @OneToMany(mappedBy = "ano")
    private List<File> files;

    @PrePersist
    public void prePersist(){
        // 엔티티 기본 속성 값 초기화
        if(this.cate == null){
            this.cate = "free";
        }
    }
}
