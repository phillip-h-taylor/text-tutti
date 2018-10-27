package com.texttutti.service;

import jm.music.data.Score;
import jm.util.Write;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FileWriter {

    public void writeToFile(Score score, String fileName) {
        try {
            final String pathname = new File(".").getCanonicalPath() + "//scores//" + fileName;
            File file = new File(pathname);
            file.getParentFile().mkdirs();
            file.createNewFile();
            Write.midi(score, pathname);
        } catch (IOException e) {
            // Too bad
        }
    }
}
