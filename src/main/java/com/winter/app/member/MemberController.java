package com.winter.app.member;

import com.winter.app.member.groups.MemberJoinGroup;
import com.winter.app.member.groups.MemberUpdateGroup;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
@Slf4j
public class MemberController {
    @Autowired
    MemberService memberService;

    @ModelAttribute("title")
    public String title(){
        return "Member";
    }

    @GetMapping("join")
    public String join(@ModelAttribute MemberVO memberVO){
        return "member/join";
    }

    @PostMapping("join")
    public String join(@Validated(MemberJoinGroup.class) MemberVO memberVO, BindingResult bindingResult, Model model){
        log.info("memberVO = {}", memberVO);
//        if(bindingResult.hasErrors()){
//            return "member/join";
//        }
        boolean check = memberService.checkMember(memberVO, bindingResult);
        if(check){
            return "member/join";
        }

        int result = memberService.add(memberVO);
        model.addAttribute("result", "MemberVO.join.result");
        model.addAttribute("path", "/");

        return "commons/result";
    }

    @GetMapping("update")
    public void update(Model model) throws Exception{
        MemberVO memberVO = memberService.detail();

        model.addAttribute("memberVO", memberVO);
    }

    @PostMapping("update")
    public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "member/update";
        }

        return "redirect:../";
    }
}
