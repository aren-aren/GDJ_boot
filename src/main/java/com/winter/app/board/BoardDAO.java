package com.winter.app.board;

import com.winter.app.board.notice.NoticeVO;
import com.winter.app.util.Pager;

import java.util.List;

public interface BoardDAO {

    List<BoardVO> getList(Pager pager) throws Exception;
    int add(BoardVO boardVO) throws Exception;
    Long getTotalCount(Pager pager) throws Exception;
    int addFile(List<FileVO> fileVOList) throws Exception;

    BoardVO getDetail(BoardVO boardVO);

    FileVO getFileDetail(FileVO fileVO) throws Exception;
}
