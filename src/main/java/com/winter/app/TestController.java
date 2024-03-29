package com.winter.app;

import com.winter.app.member.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
/*
 *  DTO (Data Transfer Object)
 *   - 가변성
 *
 *  VO (Value Object)
 *   - 불변성
 *   - setter가 없으며 생성자를 통해 값을 입력
 *
 * */

@Controller
@Slf4j
public class TestController {

    @GetMapping("/")
    public String test() throws InterruptedException {

        return "index";
    }

    @GetMapping("/expired")
    public String expired(Model model) {
        model.addAttribute("result", "sessionManagement.logout.notice");
        model.addAttribute("path", "/");

        return "commons/result";
    }
}
