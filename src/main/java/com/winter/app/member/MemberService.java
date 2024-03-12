package com.winter.app.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService implements UserDetailsService {
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
