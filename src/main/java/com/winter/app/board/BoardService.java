package com.winter.app.board;

import com.winter.app.util.Pager;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    List<BoardVO> getList(Pager pager) throws Exception;
    int add(BoardVO boardVO, MultipartFile[] attach) throws Exception;

    BoardVO getDetail(BoardVO boardVO);

    FileVO getFileDetail(FileVO fileVO) throws Exception;
}
