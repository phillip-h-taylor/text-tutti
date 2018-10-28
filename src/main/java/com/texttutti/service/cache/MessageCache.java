package com.texttutti.service.cache;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageCache {

    private Map<Long, String> messageMap;
    private Map<Long, LocalDateTime> lastUpdatedMap;

    public MessageCache() {
        this.messageMap = new HashMap<>();
        this.lastUpdatedMap = new HashMap<>();
    }

    public String get(Long from) {
        final LocalDateTime lastUpdated = lastUpdatedMap.get(from);
        final LocalDateTime now = LocalDateTime.now();
        if (lastUpdated != null && Duration.between(lastUpdated, now).compareTo(Duration.ofMinutes(5)) > 0) {
            messageMap.put(from, null);
        }

        return messageMap.get(from);
    }

    public void set(Long from, String message) {
        lastUpdatedMap.put(from, LocalDateTime.now());
        messageMap.put(from, message);
    }

    public void delete(Long from) {
        lastUpdatedMap.remove(from);
        messageMap.remove(from);
    }
}
