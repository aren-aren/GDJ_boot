package com.winter.app.config;

import com.winter.app.member.MemberVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler {

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String adminKey;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("Logout Success Handler");
        MemberVO memberVO = (MemberVO) authentication.getPrincipal();

        if(memberVO.getSocial() == null){
            log.info("일반 로그아웃");
            response.sendRedirect("/");
            return;
        }

        if(memberVO.getSocial().equalsIgnoreCase("kakao")){
            log.info("카카오 로그아웃");
            WebClient webClient = WebClient.create();

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("target_id_type", "user_id");
            map.add("target_id", memberVO.getUsername());

            String result = webClient.post()
                    .uri("https://kapi.kakao.com/v1/user/logout")
                    .header("Authorization", "KakaoAK " + adminKey)
                    .body(BodyInserters.fromFormData(map))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            log.info("kakao Logout = {}\n {}" , result, memberVO.getUsername());
        }

        response.sendRedirect("/");
    }
}
