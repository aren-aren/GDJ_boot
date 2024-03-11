package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .requestMatchers("/images/**")
                .requestMatchers("/css/**")
                .requestMatchers("/js/**")
                .requestMatchers("/vender/**")
                .requestMatchers("/favicon/**")
                ;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security
                //권한에 관련된 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/member/join").permitAll()
                        .requestMatchers("/notice/add").hasRole("ADMIN")
                        .requestMatchers("/notice/update").hasAnyRole("ADMIN", "MANAGER")
                        .anyRequest().permitAll()
                )
                //로그인 관련된 설정
                .formLogin(login -> login
                        .loginPage("/member/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                );


        return security.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        //password를 암호화 해주는 객체
        return new BCryptPasswordEncoder();
    }
}
