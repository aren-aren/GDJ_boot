package com.winter.app.board;

import com.winter.app.util.Pager;

import java.util.List;

public interface BoardService {
    List<BoardVO> getList(Pager pager) throws Exception;
    int add(BoardVO boardVO) throws Exception;

}
