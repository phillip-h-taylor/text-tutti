package com.texttutti.service.translator;

import com.texttutti.service.model.Chord;
import jm.music.data.Note;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ChordTranslator {

    public Chord translate(String input) {
        final String stringBit = input.split("[0-9]")[0];
        final String numberBit = input.substring(stringBit.length());
        if (StringUtils.equals("R", stringBit)) {
            final double rhythmValueInSemis = numberBit.length() == 0 ? 1 : 1 * Double.parseDouble(numberBit);
            return new Chord(new int[]{Note.REST}, 0.25*rhythmValueInSemis);
        }
        final Integer rhythmValueInSemis = numberBit.length() == 1 ? 1 : Integer.parseInt(numberBit.substring(1));
        final double rhythmValue = 0.25*rhythmValueInSemis.doubleValue();
        List<String> pitchNames = getNotesFromChord(stringBit);
        Integer octave = Integer.parseInt(numberBit.substring(0, 1));
        Integer lowestPitch = 12 * octave + getAdditionalPitchFromNote(pitchNames.get(0));
        if (pitchNames.size() == 1) {
            return new Chord(new int[]{lowestPitch}, rhythmValue);
        }
        List<Integer> intervals = new ArrayList<>();
        for (int i = 1; i < pitchNames.size(); i++) {
            int interval = getAdditionalPitchFromNote(pitchNames.get(i)) - getAdditionalPitchFromNote(pitchNames.get(i - 1));
            if (interval < 0) {
                interval += 12;
            }
            intervals.add(interval);
        }
        int[] pitches = new int[pitchNames.size()];
        pitches[0] = lowestPitch;
        Integer currentPitch = lowestPitch;
        for (int i=0; i<intervals.size(); i++) {
            final int newPitch = currentPitch + intervals.get(i);
            pitches[i+1] = newPitch;
            currentPitch = newPitch;
        }
        return new Chord(pitches, rhythmValue);
    }

    private List<String> getNotesFromChord(String stringBit) {
        List<String> notes = new ArrayList<>();
        do {
            List<Character> sharpOrFlatIndicator = Arrays.asList('S', 'T');
            final boolean firstNoteIsSharpOrFlat = stringBit.length() > 1 && sharpOrFlatIndicator.contains(stringBit.charAt(1));
            int lengthOfFirstChordString = firstNoteIsSharpOrFlat ? 2 : 1;
            notes.add(stringBit.substring(0, lengthOfFirstChordString));
            stringBit = stringBit.substring(lengthOfFirstChordString);
        } while (stringBit.length() > 0);
        return notes;
    }

    private int getAdditionalPitchFromNote(String stringBit) {
        switch (stringBit) {
            case "BS": case "C":
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
