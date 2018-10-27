package com.texttutti.service;

import com.texttutti.adapter.MessageSender;
import com.texttutti.service.model.TranslationResponse;
import com.texttutti.service.translator.MessageTranslator;
import jm.music.data.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MusicRecorder musicRecorder;
    private final MessageTranslator messageTranslator;
    private final FileWriter fileWriter;
    private final MessageSender messageSender;

    @Autowired
    public MessageService(MusicRecorder musicRecorder, MessageTranslator messageTranslator, FileWriter fileWriter, MessageSender messageSender) {
        this.musicRecorder = musicRecorder;
        this.messageTranslator = messageTranslator;
        this.fileWriter = fileWriter;
        this.messageSender = messageSender;
    }

    public void processMessage(String id, Long from, String content) {
        final TranslationResponse translationResponse = messageTranslator.translateMessage(content);
//        final Score score = musicRecorder.recordSomething();
        final String fileName = translationResponse.getFileName();
        fileWriter.writeToFile(translationResponse.getScore(), fileName);
        String downloadPath = String.format("/retrieve/%s", fileName);
        messageSender.sendMessage(from, String.format("Score now available for download at %s", downloadPath));
    }
}
