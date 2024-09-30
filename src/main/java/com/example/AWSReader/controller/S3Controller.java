package com.example.AWSReader.controller;

import com.example.AWSReader.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s3/bucket")
@Slf4j
public class S3Controller {
    @Autowired
    private S3Service service;

    @GetMapping("/read")
    public String getBucketData(@RequestParam String bucketName,@RequestParam String key){
        log.info("S3 DataRead Successful.");
        return service.readDataFromS3(bucketName,key);
    }
}
