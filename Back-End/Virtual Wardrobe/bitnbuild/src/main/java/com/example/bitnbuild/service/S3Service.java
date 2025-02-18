package com.example.bitnbuild.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


import java.io.IOException;

@Service
public class S3Service {

    @Autowired
    private S3Client s3Client;

    private final String bucketName = "sarvesh-hotel-images"; // Your S3 bucket name

    public String uploadImageToS3(MultipartFile image, String fileName) throws IOException {
        //Path tempFile = Files.createTempFile(fileName, ".png");
        // image.transferTo(tempFile);

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(image.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(image.getInputStream(), image.getSize()));

        return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
    }
}