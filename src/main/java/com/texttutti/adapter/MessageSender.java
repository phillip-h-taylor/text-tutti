package com.texttutti.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class MessageSender {

    private static final String API_KEY = "489fef185870d03e80f0a32e7b0fc743608df298";
    private static final String FROM = "Text Tutti";

    private final RestTemplate restTemplate;

    @Autowired
    public MessageSender(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void sendMessage(Long to, String content) {
        byte[] contentAsBytes = content.getBytes(ISO_8859_1);
        String encodedContent = new String(contentAsBytes, UTF_8);
        final String path = String.format("https://api.clockworksms.com/http/send.aspx?key=%s&from=%s&to=%s&content=%s",
                API_KEY, FROM, to, encodedContent);
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(path, String.class);
        final HttpStatus statusCode = responseEntity.getStatusCode();
    }
}
