package com.winter.app.config;

import com.winter.app.member.MemberVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class SecurityLoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message = "";

        if(exception instanceof BadCredentialsException){
            message = "비밀번호를 확인하세요.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            message = "존재하지 않는 아이디 입니다.";
        } else if (exception instanceof AccountExpiredException) {
            message = "만료된 계정입니다.";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "비번 유효기간 종료.";
        } else if (exception instanceof DisabledException) {
            message = "휴면 계정입니다.";
        } else {
            message = "알 수 없는 오류입니다.";
        }

//        message = switch (exception.getClass().getSimpleName()){
//            case "BadCredentialsException"
//                    -> "비밀번호를 확인하세요.";
//            case "InternalAuthenticationServiceException"
//                    -> "존재하지 않는 아이디 입니다.";
//            case "AccountExpiredException"
//                    -> "만료된 계정입니다.";
//            case "CredentialsExpiredException"
//                    -> "비밀번호 유효기간이 만료되었습니다.";
//            case "DisabledException"
//                    -> "휴면 계정입니다.";
//            default
//                    -> "알 수 없는 오류";
//        };


        response.sendRedirect(String.format("/member/login?message=%s", URLEncoder.encode(message, StandardCharsets.UTF_8)));
    }
}
