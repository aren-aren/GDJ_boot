package com.winter.app.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@Slf4j
public class MemberService implements UserDetailsService {
    @Autowired
    MemberDAO memberDAO;

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
        return memberDAO.add(memberVO);
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
}
