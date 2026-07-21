package com.travel.userservice;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserJpaRepository extends JpaRepository<UsersEntity, Long> {


    UsersEntity findByUserID(String userID);

    UsersEntity findByNameAndEmail(String name, String email);

    UsersEntity findByEmail(String email);

    List<UsersEntity> findByUserIDContainingOrNameContaining(String userID, String name);

}
