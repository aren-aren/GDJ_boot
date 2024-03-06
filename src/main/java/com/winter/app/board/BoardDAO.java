package com.winter.app.board;

import com.winter.app.util.Pager;

import java.util.List;
import java.util.Map;

public interface BoardDAO {

    List<BoardVO> getList(Pager pager) throws Exception;
    int add(BoardVO boardVO) throws Exception;

    Long getTotalCount(Pager pager) throws Exception;
}
