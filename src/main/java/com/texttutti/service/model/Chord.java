package com.texttutti.service.model;

public class Chord {

    private final int[] pitches;
    private final Double rhythmValue;

    public Chord(int[] pitches, Double rhythmValue) {
        this.pitches = pitches;
        this.rhythmValue = rhythmValue;
    }

    public int[] getPitches() {
        return pitches;
    }

    public Double getRhythmValue() {
        return rhythmValue;
    }
}
