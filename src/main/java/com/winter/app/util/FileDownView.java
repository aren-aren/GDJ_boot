package com.winter.app.util;

import com.winter.app.board.FileVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component("fileDownView")
@Slf4j
public class FileDownView extends AbstractView {
    @Value("${app.upload.basePath}")
    private String base;

    /*
        Custom View 역할을 할 class 생성
        Controller에서 리턴한 뷰 이름을 뷰 이름으로 설정

        Controller 이후의 행동을 줌???
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FileVO fileVO = (FileVO) model.get("fileVO");

        String uri = request.getRequestURI();
        uri = uri.substring(1,uri.lastIndexOf("/") + 1);

        File file = new File(base + uri, fileVO.getFileName());

        //한글 설정
        response.setCharacterEncoding("UTF-8");
        //총 파일의 크기
        response.setContentLengthLong(file.length());

        //헤더 설정
        String oriName = URLEncoder.encode(fileVO.getOriName(), StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename=\"" + oriName + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary");

        //Server의 로컬에서 파일을 읽어 오는 작업
        FileInputStream fi = new FileInputStream(file);

        //읽은 파일을 client로 전송준비
        OutputStream os = response.getOutputStream();

        //전송
        FileCopyUtils.copy(fi,os);

        //연결 해제
        os.close();
        fi.close();
    }
}
