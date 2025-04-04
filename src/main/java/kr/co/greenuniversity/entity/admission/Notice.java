package kr.co.greenuniversity.entity.admission;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.greenuniversity.dto.admission.NoticeDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table (name ="Notice")
public class Notice {

    @Id
    private int no;
    private String title;
    private String content;
    private String comment;
    private String file;
    private String hit;
    private String writer;
    private String regip;
    private String wdate;

    public NoticeDTO toDTO() {
        return NoticeDTO.builder()
                .no(this.no)
                .title(this.title)
                .content(this.content)
                .comment(this.comment)
                .file(this.file)
                .hit(this.hit)
                .writer(this.writer)
                .regip(this.regip)
                .wdate(this.wdate)
                .build();

    }

}
