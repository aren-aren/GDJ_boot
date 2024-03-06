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
    public void add() throws Exception {
        int result = 0;

        for (int i = 0; i < 100; i++) {
            NoticeVO noticeVO = new NoticeVO();
            noticeVO.setWriter("tester" + i);
            noticeVO.setBoardTitle("testTitle" + i);
            noticeVO.setBoardContents("testContents" + i);

            result += noticeDAO.add(noticeVO);
        }

        assertEquals(result,1);
    }
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
}