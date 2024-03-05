package com.winter.app.board;

import java.util.List;
import java.util.Map;

public interface BoardDAO {

    List<BoardVO> getList(Map<String, Object> map) throws Exception;
    int add(BoardVO boardVO) throws Exception;
}
