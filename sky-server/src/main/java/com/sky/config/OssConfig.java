package com.sky.config;


import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class OssConfig {

    @Autowired
    private AliOssProperties aliOssProperties;

    @Bean
    public AliOssUtil aliOssUtil(){
        String accessKeyId = aliOssProperties.getAccessKeyId();
        String accessKeySecret = aliOssProperties.getAccessKeySecret();
        String bucketName = aliOssProperties.getBucketName();
        String endpoint = aliOssProperties.getEndpoint();

        return new AliOssUtil(endpoint,accessKeyId,accessKeySecret,bucketName);
    }
}
