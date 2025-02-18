package com.example.bitnbuild.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;


@Configuration
public class AwsConfig {

    @Value("${aws.access.key}")
    private String awsAccessKey;

    @Value("${aws.secret.key}")
    private String awsSecretKey;


    //    String awsAccessKey = System.getenv("AWS_ACCESS_KEY");
//
//    String awsSecretKey = System.getenv("AWS_SECRET_KEY");
    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
                awsAccessKey, awsSecretKey);
        System.out.println(awsSecretKey);
        return S3Client.builder()
                .region(Region.of("ap-southeast-2"))  // Change to your region
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }
}
