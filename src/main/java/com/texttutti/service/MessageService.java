package com.texttutti.service;

import com.texttutti.adapter.MessageSender;
import com.texttutti.service.cache.MessageCache;
import com.texttutti.service.model.TranslationResponse;
import com.texttutti.service.translator.MessageTranslator;
import com.texttutti.service.translator.TranslationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageTranslator messageTranslator;
    private final FileWriter fileWriter;
    private final MessageSender messageSender;
    private final MessageCache messageCache;
    private final HelpService helpService;

    @Autowired
    public MessageService(MessageTranslator messageTranslator, FileWriter fileWriter, MessageSender messageSender, MessageCache messageCache, HelpService helpService) {
        this.messageTranslator = messageTranslator;
        this.fileWriter = fileWriter;
        this.messageSender = messageSender;
        this.messageCache = messageCache;
        this.helpService = helpService;
    }

    public void processMessage(Long from, String content) {
        if (content.toUpperCase().contains("HELP")) {
            final List<String> helpMessages = helpService.getHelpMessages();
            helpMessages.forEach(msg -> messageSender.sendMessage(from, msg));
        } else {
            final String cachedMessage = messageCache.get(from);
            String extendedMessage;
            if (cachedMessage == null) {
                extendedMessage = content;
            } else {
                extendedMessage = cachedMessage + content;
            }
            if (content.toUpperCase().endsWith("END")) {
                final String stringToTranslate = extendedMessage.replaceAll("END", "");
                final TranslationResponse translationResponse;
                try {
                    translationResponse = messageTranslator.translateMessage(stringToTranslate);
                    final String fileName = translationResponse.getFileName();
                    fileWriter.writeToFile(translationResponse.getScore(), fileName);
                    String downloadPath = String.format("https://texttutti.herokuapp.com/retrieve/%s", fileName);
                    messageSender.sendMessage(from, String.format("Score now available for download at %s", downloadPath));
                } catch (TranslationException e) {
                    messageSender.sendMessage(from, "Could not convert into music, sorry! You'll have to start again.");
                } finally {
                    messageCache.delete(from);
                }
            } else {
                messageCache.set(from, extendedMessage);
                messageSender.sendMessage(from, "Message received, please continue");
            }
        }
    }
}
