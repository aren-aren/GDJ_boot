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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class MemberVO implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleVOs.stream()
                .map(RoleVO::getRoleName)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
