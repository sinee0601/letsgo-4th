package com.travel.userservice;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "password" })
public class UserRequest {
    private String userId;
    private String name;
    private String email;
    private String password;
}
