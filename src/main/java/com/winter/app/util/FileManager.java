package com.winter.app.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class FileManager {
    public String fileSave(String path, MultipartFile multipartFile) throws Exception{
        File file = new File(path);

        if(!file.exists()) file.mkdirs();

        // 1. 파일 이름 생성
        String filename = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

        // 2. 파일 저장
        file = new File(file, filename);
        multipartFile.transferTo(file);

        return filename;
    }
}
