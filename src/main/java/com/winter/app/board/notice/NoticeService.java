package com.winter.app.board.notice;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.util.FileManager;
import com.winter.app.util.Pager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NoticeService implements BoardService {

    @Autowired
    private BoardDAO noticeDAO;
    @Autowired
    private FileManager fileManager;
    @Value("${app.upload.board.notice}")
    private String uploadPath;

    @Override
    public List<BoardVO> getList(Pager pager) throws Exception {
        pager.makeIndex();
        Long totalCount = noticeDAO.getTotalCount(pager);

        pager.makeBlock(totalCount);
        return noticeDAO.getList(pager);
    }

    @Override
    @Transactional
    public int add(BoardVO boardVO, MultipartFile[] attach) throws Exception {
        int result = noticeDAO.add(boardVO);

        List<FileVO> fileVOList = new ArrayList<>();
        for (MultipartFile multipartFile : attach) {
            if(multipartFile.isEmpty()){
                continue;
            }

            String filename = fileManager.fileSave(uploadPath, multipartFile);
            log.warn("fileName : {}", filename);

            FileVO fileVO = new FileVO();
            fileVO.setBoardNum(boardVO.getBoardNum());
            fileVO.setFileName(filename);
            fileVO.setOriName(multipartFile.getOriginalFilename());

           fileVOList.add(fileVO);
        }

        noticeDAO.addFile(fileVOList);

        return result;
    }

    @Override
    public BoardVO getDetail(BoardVO boardVO) {
        return noticeDAO.getDetail(boardVO);
    }
}
