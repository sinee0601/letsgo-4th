package com.travel.userservice;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UsersEntity {
    @Id
    private String userEncodedId;
    @Column(unique = true, nullable = true)
    private String userId;
    @Column(unique = true, nullable = true)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    @Builder.Default
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int warningCount = 0;
    @Builder.Default
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean enabled = true;
}
