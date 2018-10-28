package com.texttutti.service.translator;

import com.texttutti.service.model.Chord;
import com.texttutti.service.model.TranslationResponse;
import jm.music.data.CPhrase;
import jm.music.data.Part;
import jm.music.data.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MessageTranslator {

    private final ChordTranslator chordTranslator;

    @Autowired
    public MessageTranslator(ChordTranslator chordTranslator) {
        this.chordTranslator = chordTranslator;
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
            final CPhrase cphrase = new CPhrase();
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
                        final Chord chord = chordTranslator.translate(comp.toUpperCase());

                        cphrase.addChord(chord.getPitches(), chord.getRhythmValue());
                    }
                } catch (Exception e) {
                    throw new TranslationException(e);
                }
            }
            part.addCPhrase(cphrase);
            score.add(part);
            channel++;
        }

        return new TranslationResponse(score, fileName);

    }
}
