package com.texttutti.service;

import com.texttutti.adapter.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageSender messageSender;
    private final MusicRecorder musicRecorder;

    @Autowired
    public MessageService(MessageSender messageSender, MusicRecorder musicRecorder) {
        this.messageSender = messageSender;
        this.musicRecorder = musicRecorder;
    }

    public void processMessage(String id, Long from, String content) {
        musicRecorder.recordSomething();
        messageSender.sendMessage(from, String.format("Message received successfully. Original content: %s", content));
    }
}
