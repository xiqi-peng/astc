package com.astc.controller;

import com.astc.file.FastDFSFile;
import com.astc.util.FastClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName FileController
 * @Description
 * @Author 彭茜奇
 * @Date 17:05 2020/4/13
 * @Version 2.1
 **/
@RestController
@CrossOrigin
public class FileController {
    @PostMapping(value = "/upload")
    public String upload(MultipartFile file) throws IOException {
        String md5 = "彭茜奇";
        String author = "tom";
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),
                file.getBytes(),
                StringUtils.getFilenameExtension(file.getOriginalFilename()), md5,author );
        String[] uploads = FastClient.uploadFile(fastDFSFile);
        String url = FastClient.getUrl() + "/" + uploads[0] + "/" + uploads[1];
        return url;
    }
}

