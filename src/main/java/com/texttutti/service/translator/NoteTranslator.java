package com.texttutti.service.translator;

import jm.music.data.Note;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class NoteTranslator {

    public Note translate(String input) {
        final String stringBit = input.split("[0-9]")[0];
        final String numberBit = input.substring(stringBit.length());
        if (StringUtils.equals("R", stringBit)) {
            final double rhythmValueInSemis = numberBit.length() == 0 ? 1 : 1 * Double.parseDouble(numberBit);
            return new Note(Note.REST, 0.25*rhythmValueInSemis);
        }
        return translateActualNote(stringBit, numberBit);
    }

    private Note translateActualNote(String stringBit, String numberBit) {
        Integer octave = Integer.parseInt(numberBit.substring(0, 1));
        Integer pitch = 12 * octave + getAdditionalPitchFromNote(stringBit);
        final Integer rhythmValueInSemis = numberBit.length() == 1 ? 1 : Integer.parseInt(numberBit.substring(1));
        return new Note(pitch, 0.25*rhythmValueInSemis.doubleValue());
    }

    private int getAdditionalPitchFromNote(String stringBit) {
        switch (stringBit) {
            case "Bs": case "C":
                return 0;
            case "CS": case "DT":
                return 1;
            case "D":
                return 2;
            case "DS": case "ET":
                return 3;
            case "E": case "FT":
                return 4;
            case "F":
                return 5;
            case "FS": case "GT":
                return 6;
            case "G":
                return 7;
            case "GS": case "AT":
                return 8;
            case "A":
                return 9;
            case "AS": case "BT":
                return 10;
            case "B": case "CT":
                return 11;
            default:
                return 0;
        }
    }
}
