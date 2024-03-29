package com.winter.app.board.qna;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/qna/*")
public class QnaController {

    @Autowired
    private BoardService qnaService;

    @GetMapping("list")
    public String getList(Pager pager, Model model) throws Exception {
        List<BoardVO> list = qnaService.getList(pager);

        model.addAttribute("list", list);

        return "board/list";
    }
}
