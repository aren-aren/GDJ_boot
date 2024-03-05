package com.winter.app.board;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
public class BoardVO {
    private Long boardNum;
    private String writer;
    private String boardTitle;
    private String boardContents;
    private Date boardDate;
    private Long boardViews;
}
