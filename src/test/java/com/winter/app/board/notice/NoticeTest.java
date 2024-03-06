package com.winter.app.board.notice;

import com.winter.app.util.Pager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class NoticeTest {
    @Autowired
    NoticeDAO noticeDAO;
    @Test
    public void selectTest() throws Exception {
        Pager pager = new Pager();
        pager.setPage(2L);
        pager.makeIndex();
        Long totalCount = noticeDAO.getTotalCount(pager);
        pager.makeBlock(totalCount);

        log.info("{}",pager);

        var l = noticeDAO.getList(pager);

        assertEquals(l.size(), pager.getPerPage());
    }

    @Test
    public void getDetail() throws Exception {
        NoticeVO noticeVO = new NoticeVO();
        noticeVO.setBoardNum(206L);

        NoticeVO notice = (NoticeVO) noticeDAO.getDetail(noticeVO);

        log.info("notice = {}", notice);
        log.info("notice = {}", notice);
        log.info("notice = {}", notice);
        log.info("notice = {}", notice);
        log.info("notice = {}", notice);
        log.info("notice = {}", notice);
    }
}