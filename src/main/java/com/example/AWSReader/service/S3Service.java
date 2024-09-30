package com.example.AWSReader.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3Client;  // Inject AmazonS3 directly instead of S3Config

    public String readDataFromS3(String bucketName, String key) {
        StringBuilder readData = new StringBuilder();
        S3Object s3Object = null;

        try {
            s3Object = s3Client.getObject(new GetObjectRequest(bucketName, key));  // Use GetObjectRequest for clarity
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(s3Object.getObjectContent(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    readData.append(line).append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error reading data from S3: " + e.getMessage();
        } finally {
            if (s3Object != null) {
                try {
                    s3Object.close();  // Always close the S3Object
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return readData.toString();
    }
}
