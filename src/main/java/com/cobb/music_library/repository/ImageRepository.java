package com.cobb.music_library.repository;

import com.cobb.music_library.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    // You can add custom query methods here if needed
}