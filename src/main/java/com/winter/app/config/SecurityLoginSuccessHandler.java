package com.winter.app.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class SecurityLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final String REMEMBERID = "rememberId";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        /*
            로그인 성공시 처리할 과정들을 수행
         */
        String rememberId = request.getParameter(REMEMBERID);
        if(rememberId != null){
            String username = authentication.getName();
            Cookie cookie = new Cookie(REMEMBERID, username);
            cookie.setMaxAge(300);  // 초단위 가용 시간
            cookie.setPath("/");    // 사용할 url

            response.addCookie(cookie);
        } else {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(REMEMBERID)) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        log.info("로그인 성공");
        response.sendRedirect("/");
    }
}
