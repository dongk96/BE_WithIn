package com.example.within.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service

public class S3Uploader {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getSize());
        amazonS3.putObject(bucket, fileName, multipartFile.getInputStream(), objMeta);

        return amazonS3.getUrl(bucket, fileName).toString();
    }

    public void delete(String fileUrl) {
        try {
            String[] temp = fileUrl.split("/");
            String fileKey = temp[temp.length-1];
            amazonS3.deleteObject(bucket, fileKey);
        } catch (Exception ignored) {
        }
    }

    public String update(String oldFile, MultipartFile newFile) throws IOException {
        // 기존 파일 삭제
        delete(oldFile);

        // 새로운 파일 업로드
        return upload(newFile);
    }
}

