package com.cobb.music_library.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cobb.music_library.model.Part;
import com.cobb.music_library.service.PartService;

@RestController
@RequestMapping("/api/parts")
public class PartController {

    @Autowired
    private PartService partService;

    @PostMapping
    public ResponseEntity<Part> createPart(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("partType") Part.PartType partType,
            @RequestParam("clef") Part.ClefType clef,
            @RequestParam("genre") String genre,
            @RequestParam("file") MultipartFile file) {
        try {
            Part createdPart = partService.createPart(title, author, partType, clef, genre, file);
            return ResponseEntity.ok(createdPart);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Add other endpoints as needed (e.g., GET, PUT, DELETE)
}