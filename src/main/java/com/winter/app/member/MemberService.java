package com.winter.app.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService extends DefaultOAuth2UserService implements UserDetailsService {
    @Autowired
    MemberDAO memberDAO;
    @Autowired
    PasswordEncoder passwordEncoder;

    //add 검증 메서드
    //비번 일치, id 중복 여부
    public boolean checkMember(MemberVO memberVO, BindingResult bindingResult){
        /* check가 true면 실패,
                   false면 성공 */
        boolean check = false;
        // annotation 검증 결과
        check = bindingResult.hasErrors();

        // 비밀번호 일치 여부
        if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())){
            check = true;
            bindingResult.rejectValue("passwordCheck", "MemberVO.password.notEquals");
        }

        // id 중복
        MemberVO result = memberDAO.getDetail(memberVO);
        if(result != null){
            check = true;
            bindingResult.rejectValue("username", "MemberVO.username.duplicate");
        }

        return check;
    }

    public int add(MemberVO memberVO) {
        //비밀번호 암호화
        memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
        int result = memberDAO.add(memberVO);

        //회원의 ROLE 정보 저장
        result += 10*memberDAO.addMemberRole(memberVO);

        return result;
    }

    private OAuth2User kakao(OAuth2User oAuth2User){
        Map<String, Object> map = oAuth2User.getAttribute("properties");

        MemberVO memberVO = new MemberVO();
        memberVO.setUsername(oAuth2User.getName());
        memberVO.setName(map.get("nickname").toString());
        memberVO.setAttributes(map);

        List<RoleVO> roleVOList = new ArrayList<>();
        RoleVO roleVO = new RoleVO();

        roleVO.setRoleName("ROLE_MEMBER");
        roleVO.setRoleNum(3L);

        roleVOList.add(roleVO);
        memberVO.setRoleVOs(roleVOList);

        return memberVO;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO memberVO = new MemberVO();
        memberVO.setUsername(username);

        log.info("---------------로그인 진행 --------------");
        log.info("username = {}", username);

        try {
            memberVO = memberDAO.getDetail(memberVO);
            log.info("username = {}", memberVO);
        } catch (Exception e){
            e.printStackTrace();
        }

        return memberVO;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("kakao login = {}" ,userRequest);

        ClientRegistration c = userRequest.getClientRegistration();
        String clientId = c.getClientId();
        String clientName = c.getClientName();

        log.info("id = {} , name = {}", clientId, clientName);

        OAuth2User auth2User = super.loadUser(userRequest);

        if(c.getClientName().equalsIgnoreCase("kakao")){
            auth2User = this.kakao(auth2User);
        }

        log.info("user = {}", auth2User);

        return auth2User;
    }
}
