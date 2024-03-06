package com.winter.app.board;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class BoardVO {
    private Long boardNum;
    private String writer;
    private String boardTitle;
    private String boardContents;
    private Date boardDate;
    private Long boardViews;
    private List<FileVO> fileList;
}
