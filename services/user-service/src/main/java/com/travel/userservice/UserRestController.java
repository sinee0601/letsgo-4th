package com.travel.userservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody UserRequest userRequest) {
        String userId = userRequest.getUserId();
        String name = userRequest.getName();
        String email = userRequest.getEmail();
        String password = userRequest.getPassword();

        if (userId == null || name == null || email == null || password == null
                || userId.trim().isEmpty() || name.trim().isEmpty()
                || email.trim().isEmpty() || password.trim().isEmpty()) {
            throw new InvalidInputException("필수 항목을 모두 입력해주세요.");
        }

        if (!userService.idCheck(userId)) {
            throw new DuplicateUserIdException("이미 사용 중인 아이디입니다.");
        }

        if (!userService.emailCheck(email)) {
            throw new DuplicateUserIdException("이미 사용 중인 이메일입니다.");
        }

        if (!userService.signUp(UserVO.builder()
                .userId(userId)
                .email(email)
                .name(name)
                .password(password)
                .build())) {
            throw new RuntimeException("회원가입에 실패했습니다.");
        }

        return ResponseEntity.ok(Map.of(
                "result", "success",
                "message", "회원가입 완료. 로그인 해주세요.",
                "url", "/user/loginView"));
    }


}
