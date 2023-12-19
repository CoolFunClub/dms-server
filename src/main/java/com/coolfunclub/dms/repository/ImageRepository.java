package com.coolfunclub.dms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coolfunclub.dms.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    // Custom query methods if needed
}
