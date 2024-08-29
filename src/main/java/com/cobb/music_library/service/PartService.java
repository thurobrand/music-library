package com.cobb.music_library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cobb.music_library.model.Part;
import com.cobb.music_library.repository.PartRepository;

import java.io.IOException;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    public Part createPart(String title, String author, Part.PartType partType,
                           Part.ClefType clef, String genre, MultipartFile file) throws IOException {
        Part part = new Part();
        part.setTitle(title);
        part.setAuthor(author);
        part.setPartType(partType);
        part.setClef(clef);
        part.setGenre(genre);
        part.setPdfFile(file.getBytes());

        return partRepository.save(part);
    }

    // Add other methods as needed (e.g., getPart, updatePart, deletePart)
}