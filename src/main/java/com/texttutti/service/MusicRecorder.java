package com.texttutti.service;

import jm.JMC;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import org.springframework.stereotype.Component;

@Component
public class MusicRecorder {

    public Score recordSomething() {
        final Score score = new Score("Test score");
        final Part part = new Part();
        final Phrase phrase = new Phrase("Honky Tonk", 0.0, 3);
        phrase.add(new Note(64, JMC.CROTCHET));
        phrase.add(new Note(66, JMC.CROTCHET));
        phrase.add(new Note(68, JMC.CROTCHET));
        phrase.add(new Note(69, JMC.CROTCHET));
        phrase.add(new Note(71, JMC.CROTCHET));
        phrase.add(new Note(73, JMC.CROTCHET));
        phrase.add(new Note(74, JMC.CROTCHET));
        phrase.add(new Note(76, JMC.CROTCHET));
        part.add(phrase);
        score.add(part);
        return score;
    }

}
