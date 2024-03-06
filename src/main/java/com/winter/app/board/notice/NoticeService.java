package com.winter.app.board.notice;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService implements BoardService {

    @Autowired
    private BoardDAO noticeDAO;

    @Override
    public List<BoardVO> getList(Pager pager) throws Exception {
        pager.makeIndex();
        Long totalCount = noticeDAO.getTotalCount(pager);

        pager.makeBlock(totalCount);
        return noticeDAO.getList(pager);
    }

    @Override
    public int add(BoardVO boardVO) throws Exception {
        int result = noticeDAO.add(boardVO);

        return result;
    }
}
