package com.winter.app;

import com.winter.app.board.BoardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String test(){
        log.error("Error Message");
        log.warn("warning Message");
        log.info("Info Message");
        log.debug("Debug Message");
        log.trace("Trace Message");
        return "index";
    }
}
