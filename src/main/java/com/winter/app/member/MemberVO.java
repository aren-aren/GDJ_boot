package com.winter.app.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Singular;

@Data
public class MemberVO {
    @NotBlank(message = "꼭 입력하세요")
    private String username;
    @NotBlank
    @Size(min = 8, max = 16)
    private String password;
    private String passwordCheck;
    private String phone;
    @Email
    private String email;
    private String address;
    private String name;
}
