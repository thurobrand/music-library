package com.cobb.music_library.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cobb.music_library.model.ScoreMetadata;

@Service
public class ScoreParseService {

    @Autowired
    private OpenAIService openAIService;

    public ScoreMetadata parseAndExtractMetadata(MultipartFile file) throws IOException {
        String fileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
        
        String prompt = "Analyze the following musical score and extract metadata such as title, composer, key signature, time signature, and tempo. If any information is not present or cannot be determined, indicate as 'Unknown'. Score content:\n\n" + fileContent;
        
        String aiResponse = openAIService.generateResponse(prompt);
        
        return parseAIResponseToScoreMetadata(aiResponse);
    }

    private ScoreMetadata parseAIResponseToScoreMetadata(String aiResponse) {
        // Parse the AI response and create a ScoreMetadata object
        // This is a simplified example and may need to be adjusted based on the AI's response format
        ScoreMetadata metadata = new ScoreMetadata();
        String[] lines = aiResponse.split("\n");
        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String key = parts[0].trim().toLowerCase();
                String value = parts[1].trim();
                switch (key) {
                    case "title":
                        metadata.setTitle(value);
                        break;
                    case "composer":
                        metadata.setComposer(value);
                        break;
                    case "key signature":
                        metadata.setKeySignature(value);
                        break;
                    case "time signature":
                        metadata.setTimeSignature(value);
                        break;
                  
                }
            }
        }
        return metadata;
    }
}