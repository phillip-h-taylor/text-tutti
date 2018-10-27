package com.texttutti.service.model;

import jm.music.data.Score;

public class TranslationResponse {

    private final Score score;
    private final String fileName;

    public TranslationResponse(Score score, String fileName) {
        this.score = score;
        this.fileName = fileName;
    }

    public Score getScore() {
        return score;
    }

    public String getFileName() {
        return fileName;
    }
}
