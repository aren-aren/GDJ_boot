package com.winter.app.board.qna;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class QnaService implements BoardService {
    @Autowired
    BoardDAO qnaDAO;
    @Override
    public List<BoardVO> getList(Pager pager) throws Exception {
        pager.makeIndex();
        Long totalCount = qnaDAO.getTotalCount(pager);

        pager.makeBlock(totalCount);
        return qnaDAO.getList(pager);
    }

    @Override
    public int add(BoardVO boardVO, MultipartFile[] attach) throws Exception {
        return 0;
    }

    @Override
    public BoardVO getDetail(BoardVO boardVO) {
        return null;
    }

    @Override
    public FileVO getFileDetail(FileVO fileVO) throws Exception {
        return null;
    }
}
