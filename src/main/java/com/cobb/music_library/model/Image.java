package com.cobb.music_library.model;

import jakarta.persistence.*;

@Entity
@Table(name = "images", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name", "imagePath"})
})
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imagePath;

    @Lob
    @Column(nullable = false)
    private byte[] data;

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    String getImagePath() {
        return imagePath;
    }

    public byte[] getData() {
        return data;
    }

    // Setters
    void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
} 
    

