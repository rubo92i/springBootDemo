package com.example.demo.controller;


import com.example.demo.model.Metadata;
import com.example.demo.model.exceptions.NotFoundException;
import com.example.demo.repository.MetadataRepository;
import com.example.demo.util.FileStoreHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/metadata")
public class MetadataController {


    @Autowired
    private MetadataRepository metadataRepository;

    @Autowired
    private FileStoreHelper fileStoreHelper;

    @PostMapping
    public ResponseEntity add(@RequestParam MultipartFile multipartFile, @RequestParam String description) throws IOException {
        String path = fileStoreHelper.storeFile(multipartFile);
        Metadata metadata = new Metadata();
        metadata.setPath(path);
        metadata.setDescription(description);
        metadataRepository.save(metadata);
        return ResponseEntity.ok(metadata);
    }


    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) throws NotFoundException, IOException {
        Metadata metadata = metadataRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(""));
        FileSystemResource fileSystemResource = new FileSystemResource(metadata.getPath());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header("Content-Disposition","inline; filename=file.jpg")
              //  .header("Content-Disposition","attachment; filename=file.jpg")
                .contentLength(fileSystemResource.contentLength())
                .body(fileSystemResource);
    }


}
