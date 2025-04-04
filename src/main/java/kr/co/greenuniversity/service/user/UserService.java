package kr.co.greenuniversity.service.user;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.greenuniversity.dto.user.UserDTO;
import kr.co.greenuniversity.entity.user.User;
import kr.co.greenuniversity.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    private final HttpServletRequest request;

    public void userRegister(UserDTO userDTO) {

        log.info("userDTO: {}", userDTO);

        // 비밀번호 암호화
        String encodedPass = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPass);

        // 엔티티 변환
        User user = modelMapper.map(userDTO, User.class);

        // 저장
        userRepository.save(user);
    }

    public User findUserId(UserDTO userDTO) {
        log.info("userDTO: {}", userDTO);

        User user = modelMapper.map(userDTO, User.class);

        Optional<User> optUser = userRepository.findByEmail(user.getEmail());

        if(optUser.isPresent()) {
            User userResult = optUser.get();
            return userResult;
        }
        return null;
    }

    public boolean updatePass(String id, String password) {
        log.info("id: {}", id);
        log.info("password: {}", password);

        Optional<User> optUser = userRepository.findById(id);
        log.info("db에서 찾은 유저: {}", optUser.isPresent());

        if (optUser.isPresent()) {
            User user = optUser.get();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public long checkEmail(String type, String value){
        long count = 0;
        log.info("checkUser: {}", type + value);

        String code = sendEmailCode(value);
        // 인증코드 비교를 하기 위해서 세션 저장
        HttpSession session = request.getSession();
        session.setAttribute("authCode", code);
        log.info("authCode : " + code);
        return count;
    }

    public long checkUser(String type, String value){
        long count = 0;
        log.info("checkUser: {}", type + value);


        if(type.equals("uid")){
            count = userRepository.countById(value);
        }else if(type.equals("email")){
            count = userRepository.countByEmail(value);
            if(count == 0){
                String code = sendEmailCode(value);

                // 인증코드 비교를 하기 위해서 세션 저장
                HttpSession session = request.getSession();
                session.setAttribute("authCode", code);
                log.info("authCode : " + code);
            }

        }else if(type.equals("phone")){
            count = userRepository.countByPhone(value);
            log.info("count: {}", count);
        }
        return count;
    }


    @Value("${spring.mail.username}")
    private String sender;
    public String sendEmailCode(String receiver){

        // MimeMessage 생성
        MimeMessage message = mailSender.createMimeMessage();

        // 인증코드 생성
        int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
        log.info("code : " + code);

        String subject = "greendae 인증코드 안내";
        String content = "<h1>greendae 인증코드는 " + code + "입니다.</h1>";

        try {
            // 메일 정보 설정
            message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=UTF-8");

            // 메일 발송
            mailSender.send(message);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return String.valueOf(code);
    }

}