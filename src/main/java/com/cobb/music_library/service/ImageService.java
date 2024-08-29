package com.cobb.music_library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cobb.music_library.model.Image;
import com.cobb.music_library.repository.ImageRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image storeImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Image image = new Image();
        image.setName(fileName);
        image.setImagePath("/images/" + fileName);
        image.setData(file.getBytes());
        return imageRepository.save(image);
    }

    public Image getImage(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        return image.orElse(null);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
