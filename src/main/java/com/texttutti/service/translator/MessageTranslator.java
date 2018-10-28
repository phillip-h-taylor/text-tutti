package com.texttutti.service.translator;

import com.texttutti.service.model.TranslationResponse;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MessageTranslator {

    private final NoteTranslator noteTranslator;

    @Autowired
    public MessageTranslator(NoteTranslator noteTranslator) {
        this.noteTranslator = noteTranslator;
    }

    public TranslationResponse translateMessage(String content) throws TranslationException {
        final Score score = new Score();
        String fileName = "score.mid";
        final List<String> parts = Arrays.asList(content.split("&"));
        int channel = 0;
        for (String partString : parts) {
            final List<String> components = Arrays.asList(partString.split(","));
            final Part part = new Part();
            part.setChannel(channel);
            final Phrase phrase = new Phrase();
            for (String comp : components) {
                try {
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
                    } else if (comp.toUpperCase().startsWith("V")){
                        //It's a volume
                        part.setDynamic(Integer.parseInt(comp.replaceAll("V", "")));
                    } else {
                        phrase.add(noteTranslator.translate(comp.toUpperCase()));
                    }
                } catch (Exception e) {
                    throw new TranslationException(e);
                }
            }
            part.add(phrase);
            score.add(part);
            channel++;
        }

        return new TranslationResponse(score, fileName);

    }
}
