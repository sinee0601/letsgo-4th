package com.travel.userservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public boolean signUp(UserVO userVO) {
        if (userJpaRepository.findByUserId(userVO.getUserId()) != null) {
            return false;
        }
        if (userJpaRepository.findByEmail(userVO.getEmail()) != null) {
            return false;
        }
        boolean success = (userJpaRepository.save(UserEntity.builder()
                .userEncodedId(UUID.randomUUID().toString())
                .userId(userVO.getUserId())
                .password(bCryptPasswordEncoder.encode(userVO.getPassword()))
                .email(userVO.getEmail())
                .name(userVO.getName())
                .role("ROLE_USER").build())) != null;
        if (success) {
            log.info("사용자 회원가입 성공: userID={}", userVO.getUserId());
        }
        return success;
    }

    public boolean idCheck(String userID) {
        return userJpaRepository.findByUserId(userID) == null;
    }


    public boolean emailCheck(String email) {
        return userJpaRepository.findByEmail(email) == null;
    }
}
