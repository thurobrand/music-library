package com.cobb.music_library.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "parts")
@Data
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PartType partType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClefType clef;

    @Column(nullable = false)
    private String genre;

    @Lob
    @Column(nullable = false)
    private byte[] pdfFile;

    // Enum for PartType
    public enum PartType {
        FIRST_CORNET, SECOND_CORNET, THIRD_CORNET, EB_CORNET, FLUGELHORN,
        TENOR_HORN, BARITONE, EUPHONIUM, FIRST_TROMBONE, SECOND_TROMBONE,
        THIRD_TROMBONE, BASS_TROMBONE, EB_TUBA, BB_TUBA, PERCUSSION_1,
        PERCUSSION_2, PERCUSSION_3, CONDUCTOR_SCORE, OTHER
    }

    // Enum for ClefType
    public enum ClefType {
        TREBLE, BASS
    }
}
