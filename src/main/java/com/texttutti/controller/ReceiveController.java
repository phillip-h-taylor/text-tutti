package com.texttutti.controller;

import com.texttutti.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiveController {
    private final MessageService messageService;

    @Autowired
    public ReceiveController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(path = "/receive")
    public ResponseEntity<Void> receive(@RequestParam("id") String id, @RequestParam("from") Long from, @RequestParam("content") String content) {
        messageService.processMessage(id, from, content);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
