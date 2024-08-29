package com.cobb.music_library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cobb.music_library.service.ScoreParseService;
import com.cobb.music_library.model.ScoreMetadata;

@RestController
@RequestMapping("/api/score/extract")
public class ScoreParserController {

    @Autowired
    private ScoreParseService scoreParseService;

    @PostMapping
    public ResponseEntity<ScoreMetadata> extractScoreMetadata(@RequestParam("file") MultipartFile file) {
        try {
            ScoreMetadata metadata = scoreParseService.parseAndExtractMetadata(file);
            return ResponseEntity.ok(metadata);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}