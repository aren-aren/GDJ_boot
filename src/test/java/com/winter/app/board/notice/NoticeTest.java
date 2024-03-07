package com.winter.app.board.notice;

import com.winter.app.util.Pager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class NoticeTest {
    @Autowired
    NoticeDAO noticeDAO;
    @Test
    public void insertTest() throws Exception {
        NoticeVO noticeVO = new NoticeVO();
        noticeVO.setBoardTitle("트랜잭션 테스트");
        noticeVO.setBoardContents("트랜잭션 테스트트랜잭션 테스트트랜잭션 테스트");
        noticeVO.setWriter("wiwiw");

        int add = noticeDAO.add(noticeVO);

        assertEquals(add, 1);
    }

    @Test
    public void selectTest() throws Exception {
    }

    @Test
    public void getDetail() throws Exception {
    }
}