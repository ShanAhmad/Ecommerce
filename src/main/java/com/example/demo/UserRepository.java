package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDto,Integer> {
    UserDto findByUsernameOrEmail(String username,String email);
    Boolean existsByusername(String username);

    Boolean existsByemail(String email);
}
