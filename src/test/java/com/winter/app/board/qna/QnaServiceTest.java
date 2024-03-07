package com.winter.app.board.qna;

import com.winter.app.util.Pager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class QnaServiceTest {

    @Autowired
    private QnaService qnaService;

    @Test
    void getList() throws Exception {
    }
}