package com.winter.app.board.notice;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.util.Pager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {
    @Autowired
    private BoardService noticeService;
    @Value("${board.notice.name}")
    private String name;

    @ModelAttribute("board")
    public String board(){
        return this.name;
    }
    @GetMapping("list")
    public String getList(Pager pager, Model model) throws Exception {
        var list = noticeService.getList(pager);
        model.addAttribute("list", list);
        model.addAttribute("pager", pager);

        return "board/list";
    }

    @GetMapping("detail")
    public String getDetail(BoardVO boardVO, Model model) throws Exception {
        NoticeVO noticeVO = (NoticeVO) noticeService.getDetail(boardVO);

        model.addAttribute("vo", noticeVO);

        return "board/detail";
    }

    @GetMapping("add")
    public String add() {
        return "board/add";
    }

    @PostMapping("add")
    public String add(NoticeVO noticeVO, MultipartFile[] attach) throws Exception{
        int result = noticeService.add(noticeVO, attach);
        return "redirect:/notice/list";
    }
}