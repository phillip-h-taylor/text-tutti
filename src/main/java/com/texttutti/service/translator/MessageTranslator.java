package com.texttutti.service.translator;

import com.texttutti.service.model.TranslationResponse;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

@Component
public class MessageTranslator {

    private final NoteTranslator noteTranslator;

    @Autowired
    public MessageTranslator(NoteTranslator noteTranslator) {
        this.noteTranslator = noteTranslator;
    }

    public TranslationResponse translateMessage(String content) {
        String fileName = "score.mid";
        final List<String> components = Arrays.asList(content.split(","));
        final Score score = new Score();
        final Part part = new Part();
        final Phrase phrase = new Phrase();
        for (String comp : components) {
            if (comp.toUpperCase().startsWith("FI")) {
                //It's a filename
                fileName = comp.substring(2) + ".mid";
            } else if (comp.toUpperCase().startsWith("TI")) {
                //It's a title
                score.setTitle(comp.substring(2));
            } else if (comp.toUpperCase().startsWith("I")) {
                //It's an instrument
                part.setInstrument(Integer.parseInt(comp.replaceAll("I", "")));
            } else if (comp.toUpperCase().startsWith("T")) {
                //It's a tempo
                final int tempo = Integer.parseInt(comp.replaceAll("T", ""));
                score.setTempo(tempo);
            } else {
                phrase.add(noteTranslator.translate(comp.toUpperCase()));
            }
        }

        part.add(phrase);
        score.add(part);

        return new TranslationResponse(score, fileName);
    }
}
