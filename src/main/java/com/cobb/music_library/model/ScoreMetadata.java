package com.cobb.music_library.model;

public class ScoreMetadata {
    private String title;
    private String composer;
    private String arranger;
    private String instrument;
    private String keySignature;
    private String timeSignature;

    // Constructors
    public ScoreMetadata() {}

    public ScoreMetadata(String title, String composer, String arranger, String instrument, String keySignature, String timeSignature) {
        this.title = title;
        this.composer = composer;
        this.arranger = arranger;
        this.instrument = instrument;
        this.keySignature = keySignature;
        this.timeSignature = timeSignature;
    }

    // Existing getters and setters...

    public String getKeySignature() {
        return keySignature;
    }

    public void setKeySignature(String keySignature) {
        this.keySignature = keySignature;
    }

    public String getTimeSignature() {
        return timeSignature;
    }

    public void setTimeSignature(String timeSignature) {
        this.timeSignature = timeSignature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getArranger() {
        return arranger;
    }

    public void setArranger(String arranger) {
        this.arranger = arranger;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    // You might want to override toString(), equals(), and hashCode() methods as well
}