package com.winter.app.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.include=dev")
class MemberServiceTest {

    @Autowired
    MemberDAO dao;


}