package kr.co.greenuniversity.dto.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String id;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String addr1;
    private String addr2;
    private String role;
    private String regip;
    private String regDate;
    private String leaveDate;


}