package com.coolfunclub.dms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.StorageException;
import com.coolfunclub.dms.model.Image;
import com.coolfunclub.dms.repository.ImageRepository;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ImageService {

    private final Path rootLocation = Paths.get("./dms/images"); // Define the root location for image storage
    @Autowired
    private ImageRepository imageRepository;

    public Image saveImage(MultipartFile file) throws IOException {
        // System.out.println(rootLocation.toString());
        // System.out.println(rootLocation.toAbsolutePath().toString());
        // Ensure directory exists
        // System.out.println("checking if the dir exists");
        if (!Files.exists(rootLocation)) {
            // System.out.println("it does not.. creating it at: "+ rootLocation);
            try{
                Files.createDirectories(rootLocation);
            } catch (Exception e){
                // System.out.println(e.getCause().toString());
                e.printStackTrace();
            }
            // System.out.println("finished creating it");
        }

        // Generate a unique filename
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString() + fileExtension;

        // Save the file to the disk
        Path destinationFile = rootLocation.resolve(
            Paths.get(filename))
            .normalize().toAbsolutePath();
        if (!destinationFile.getParent().normalize().equals(rootLocation.toAbsolutePath().normalize())) {
            throw new StorageException("Cannot store file outside current directory.");
        }
            
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        }

        // save image metadata
        Image image = new Image();
        image.setName(filename);
        image.setUrl(destinationFile.toString());

        return imageRepository.save(image);
    }
    public Resource loadImageAsResource(Long imageId) throws IOException {
        Image image = imageRepository.findById(imageId)
            .orElseThrow(() -> new StorageException("Image not found with id: " + imageId));

        Path filePath = Paths.get(image.getUrl()).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if(resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new StorageException("Could not read file: " + image.getName());
        }
    }


    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void deleteImage(Long id) throws IOException {
        Optional<Image> imageOpt = imageRepository.findById(id);
        if (imageOpt.isPresent()) {
            Image image = imageOpt.get();

            Files.deleteIfExists(Paths.get(image.getUrl()));

            imageRepository.deleteById(id);
        } else {
            throw new IOException("Image not found with id: " + id);
        }
    }
}


