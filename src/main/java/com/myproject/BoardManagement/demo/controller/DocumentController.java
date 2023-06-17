package com.myproject.BoardManagement.demo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import com.myproject.BoardManagement.demo.model.files;
import com.myproject.BoardManagement.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DocumentController {
//    @Autowired
//    private DocumentService documentService;

//    @GetMapping("/document")
//    public ResponseEntity<?> getDocument() {
//        Path documentPath = Path.of("/path/to/document.pdf");
//        try {
//            byte[] documentBytes = Files.readAllBytes(documentPath);
//            String base64EncodedData = Base64.getEncoder().encodeToString(documentBytes);
//            return ResponseEntity.ok().body(base64EncodedData);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Failed to read document: " + e.getMessage());
//        }

    }

