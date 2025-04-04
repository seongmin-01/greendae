package kr.co.greenuniversity.controller.user;

import jakarta.servlet.http.HttpSession;
import kr.co.greenuniversity.dto.user.UserDTO;
import kr.co.greenuniversity.entity.user.User;
import kr.co.greenuniversity.repository.user.UserRepository;
import kr.co.greenuniversity.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FindController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/findId")
    public String findId() {

        return "/user/findId";
    }

    @PostMapping("/findId")
    public String findId(UserDTO userDTO, Model model) {

        User findUser = userService.findUserId(userDTO);

        if (findUser != null) {
            log.info("찾은 유저: {}", findUser);

            model.addAttribute("resultId", findUser.getId());

            return "/user/ResultId";
        }
        log.info("사용자 없음");
        model.addAttribute("error", "해당 사용자를 찾을 수 없습니다.");
        return "/user/ResultId";
    }



    @GetMapping("/findPass")
    public String findPass() {
        return "/user/findPass";
    }

    @PostMapping("/findPass")
    public String findPass(UserDTO userDTO, Model model) {
        User findUser = userService.findUserId(userDTO);

        if (findUser != null) {
            log.info("찾은 유저: {}", findUser);

            model.addAttribute("resultUser", findUser.getId());

            return "/user/updatePass";
        }
        log.info("사용자 없음");
        model.addAttribute("error2", "해당 사용자를 찾을 수 없습니다.");
        return "/user/login";
    }

    // 새 비밀번호 설정
    @GetMapping("/updatePass")
    public String updatePass() {

        return "/user/updatePass";
    }

    // 새 비밀번호 설정
    @PostMapping("/updatePass")
    public String updatePass(@RequestParam("id") String id,
                             @RequestParam("password") String password,
                             @RequestParam("password2") String password2,
                             RedirectAttributes redirectAttributes) {
        log.info("id: {}", id);
        log.info("password: {}", password);

        boolean isUpdated = userService.updatePass(id, password);
        log.info("isUpdated: {}", isUpdated);

        if (isUpdated) {
            redirectAttributes.addFlashAttribute("success", "비밀번호가 변경되었습니다.");
            return "redirect:/user/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "유저를 찾을 수 없습니다.");
            return "redirect:/user/updatePass";
        }
    }


    // 이메일 체크
    @GetMapping("/find/{type}/{value}")
    public ResponseEntity user(@PathVariable("type") String type, @PathVariable("value") String value){
        log.info("type : " + type + ", value : " + value);

        // 서비스 호출
        long count = userService.checkEmail(type, value);
        log.info("count : " + count);

        // JSON 생성
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("count", count);
        log.info("count : " + count);

        // JSON 반환
        return ResponseEntity.ok().body(resultMap);
    }

    // 이메일 발송
    @PostMapping("/find/email/auth")
    public ResponseEntity<Boolean> emailAuth(@RequestBody Map<String,String> map, HttpSession session){ // JSON 단일 문자열값이 직접 String으로 매핑되지 않기 때문에 JSON과 호환되는 Map 타입으로 JSON 수신

        String authCode = map.get("authCode");
        log.info("authCode : {}", authCode);

        String sessAuthCode = (String) session.getAttribute("authCode");
        log.info("sessAuthCode : {}", sessAuthCode);

        if(authCode.equals(sessAuthCode)){
            return ResponseEntity
                    .ok()
                    .body(true);
        }
        return ResponseEntity
                .ok()
                .body(false);
    }

}
