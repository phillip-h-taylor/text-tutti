package com.texttutti.service;

import com.texttutti.adapter.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageSender messageSender;

    @Autowired
    public MessageService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void processMessage(String id, Long from, String content) {
        messageSender.sendMessage(from, String.format("Message received successfully. Original content: %s", content));
    }
}
