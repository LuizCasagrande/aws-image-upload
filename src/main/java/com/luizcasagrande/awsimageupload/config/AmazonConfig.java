package com.luizcasagrande.awsimageupload.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:amazon.properties")
public class AmazonConfig {

    @Value("${AWSAccessKeyId}")
    private String awsAccessKeyId;

    @Value("${AWSSecretKey}")
    private String awsSecretKey;

    @Bean
    public AmazonS3 s3() {
        var awsCredentials = new BasicAWSCredentials(
                awsAccessKeyId,
                awsSecretKey
        );

        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.SA_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
