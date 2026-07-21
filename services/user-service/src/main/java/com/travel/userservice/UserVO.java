package com.travel.userservice;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {
    private String userEncodedId;
    private String userId;
    private String email;
    private String name;
    private String password;
    private String role;
    private LocalDateTime createdAt;
    private int warningCount;
    private boolean enabled;
}
