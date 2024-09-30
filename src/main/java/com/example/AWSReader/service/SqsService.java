package com.example.AWSReader.service;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.example.AWSReader.config.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SqsService {
    @Value("${aws.sqs.queue-name}")
    private String queueName;

    @Autowired
    private SqsListener listener;

    @Scheduled(fixedDelay = 5000)
    public void receiveMessages() {
        ReceiveMessageRequest messageRequest = new ReceiveMessageRequest()
                .withQueueUrl(queueName)
                .withMaxNumberOfMessages(10);
        List<Message> messages = listener.amazonSQS().receiveMessage(messageRequest).getMessages();

        try {
            for (Message message : messages) {
                readMessage(message);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void readMessage(Message message) {
        System.out.println(message.getBody());
    }

}
