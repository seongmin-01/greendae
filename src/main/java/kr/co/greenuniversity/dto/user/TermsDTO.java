package kr.co.greenuniversity.dto.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TermsDTO {

    private int no;
    private String terms;
    private String privacy;

}
