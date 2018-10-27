package com.texttutti.service;

import com.texttutti.adapter.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageSender messageSender;
    private final MusicPlayer musicPlayer;

    @Autowired
    public MessageService(MessageSender messageSender, MusicPlayer musicPlayer) {
        this.messageSender = messageSender;
        this.musicPlayer = musicPlayer;
    }

    public void processMessage(String id, Long from, String content) {
        musicPlayer.playSomething();
        messageSender.sendMessage(from, String.format("Message received successfully. Original content: %s", content));
    }
}
