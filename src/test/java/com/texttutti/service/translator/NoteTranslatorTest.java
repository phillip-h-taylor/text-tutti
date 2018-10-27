package com.texttutti.service.translator;

import jm.music.data.Note;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NoteTranslatorTest {

    private NoteTranslator noteTranslator = new NoteTranslator();

    @Test
    public void testRestWithoutLength() {
        final Note note = noteTranslator.translate("R");
        assertThat(note.getPitch()).isEqualTo(Note.REST);
        assertThat(note.getRhythmValue()).isEqualTo(0.25);
    }

    @Test
    public void testRest() {
        final Note note = noteTranslator.translate("R10");
        assertThat(note.getPitch()).isEqualTo(Note.REST);
        assertThat(note.getRhythmValue()).isEqualTo(2.5);
    }

    @Test
    public void testCsharp() {
        final Note note = noteTranslator.translate("CS53");
        assertThat(note.getPitch()).isEqualTo(61);
        assertThat(note.getRhythmValue()).isEqualTo(0.75);
    }

    @Test
    public void testOneNumber() {
        final Note note = noteTranslator.translate("E4");
        assertThat(note.getPitch()).isEqualTo(52);
        assertThat(note.getRhythmValue()).isEqualTo(0.25);
    }

    @Test
    public void testAFlat() {
        final Note note = noteTranslator.translate("AT611");
        assertThat(note.getPitch()).isEqualTo(80);
        assertThat(note.getRhythmValue()).isEqualTo(2.75);
    }
}