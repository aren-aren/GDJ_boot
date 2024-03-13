package com.winter.app.config;

import com.winter.app.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
    Spring security 설정 방법

    1. Security Dependency 추가

    2. 사용자가 정보가 있는 UserDetails 생성
        - UserDetails를 구현한 VO 만들기

    3. 로그인 처리 UserDetailService 생성
        - UserDetailService를 구현한 Service 만들기

    4. SecurityConfig
        - WebSecurityCustomizer, SecurityFilterChain, PasswordEncoder타입의 인스턴스를 Bean으로 등록

    5. JSP에서 Security 사용
        - sec태그 사용을 위해 API추가, JSP에서 선언 후 사용
 */


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityLoginSuccessHandler successHandler;
    @Autowired
    private SecurityLoginFailHandler failHandler;
    @Autowired
    private MemberService memberService;

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .requestMatchers("/images/**")
                .requestMatchers("/css/**")
                .requestMatchers("/js/**")
                .requestMatchers("/vender/**")
                .requestMatchers("/favicon/**");
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
                        .requestMatchers("/member/page").authenticated()
                        .requestMatchers("/notice/list").authenticated()
                        .anyRequest().permitAll()
                )
                //로그인 관련된 설정
                .formLogin(login -> login
                        .loginPage("/member/login") // login page 지정
//                        .defaultSuccessUrl("/")       // 로그인 성공시 redirect할 url - handler 와 같이 사용시 나중에 나오는게 무시됨(?)
                        .successHandler(successHandler) // 로그인 성공시 처리할 일
                        .failureHandler(failHandler)    // 로그인 실패시 처리할 일
//                        .usernameParameter("id")    // parameter 이름을 username이 아니라 id를 사용하려 할 때
//                        .passwordParameter("pw")    // parameter 이름을 password가 아니라 pw를 사용하려 할 때
                        .permitAll()
                )
                //로그아웃 관련 설정
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)// 로그아웃시 session만료
                        .permitAll()
                )
                //rememberMe 관련 설정
                .rememberMe(remember -> remember
                        .rememberMeParameter("rememberMe")  // rememberMe parameter name
                        .tokenValiditySeconds(3600)          // 유효기간 초단위
                        .key("rememberMe!")                 // 키 - 암호화에 필요?
                        .userDetailsService(memberService)  // 로그인 할 때 쓰는 UserDetailService
                        .authenticationSuccessHandler(successHandler)   // 로그인 성공 시 Handler
                        .useSecureCookie(false)             // ?
                )
                //동시 접속
                .sessionManagement(sessionManagement -> sessionManagement
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                        .expiredUrl("/expired")
                );


        return security.build();
    }
}
