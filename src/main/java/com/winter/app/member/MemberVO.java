package com.winter.app.member;

import com.winter.app.member.groups.MemberJoinGroup;
import com.winter.app.member.groups.MemberUpdateGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
public class MemberVO implements UserDetails, OAuth2User {
    @NotBlank(message = "꼭 입력하세요", groups = {MemberJoinGroup.class, MemberUpdateGroup.class})
    private String username;

    @NotBlank(groups = MemberJoinGroup.class)
    @Size(min = 8, max = 16, groups = MemberJoinGroup.class)
    private String password;

    private String passwordCheck;

    private String phone;

    @Email(groups = {MemberJoinGroup.class, MemberUpdateGroup.class})
    private String email;

    private String address;

    private String name;

    private List<RoleVO> roleVOs;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    //Naver, Kakao, Google
    private String social;
    private String accessToken;
    private Map<String, Object> attributes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleVOs.stream()
                .map(RoleVO::getRoleName)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
