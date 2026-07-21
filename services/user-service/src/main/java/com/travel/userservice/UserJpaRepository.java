package com.travel.userservice;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByUserId(String userId);

    UserEntity findByNameAndEmail(String name, String email);

    UserEntity findByEmail(String email);

    List<UserEntity> findByUserIdContainingOrNameContaining(String userId, String name);

}
