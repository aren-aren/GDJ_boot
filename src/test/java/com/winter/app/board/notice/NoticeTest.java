package com.winter.app.board.notice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
        Map<String, Object> map = new HashMap<>();

        int start = 0;
        int step = 200;
        String keyword = "9";

        map.put("start", start);
        map.put("step", step);
        map.put("keyword", keyword);

        var l = noticeDAO.getList(map);
        assertEquals(l.size(), step);
    }
}