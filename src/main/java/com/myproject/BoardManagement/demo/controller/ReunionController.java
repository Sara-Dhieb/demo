package com.myproject.BoardManagement.demo.controller;

import com.myproject.BoardManagement.demo.ReunionRequest;
import com.myproject.BoardManagement.demo.model.files;
import com.myproject.BoardManagement.demo.model.Reunion;
import com.myproject.BoardManagement.demo.service.impl.ReunionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200/")
@RestController
public class ReunionController {
    @Autowired
    ReunionServiceImpl reunionServiceImpl;
    @GetMapping("/reunions")
    public ResponseEntity<List<Reunion>> findAllReunions() {
        return ResponseEntity.ok(reunionServiceImpl.findAllReunions());
    }

    @PostMapping("/reunion")
    public ResponseEntity<Reunion> saveReunion(@RequestBody ReunionRequest reunionRequest) {
        return ResponseEntity.ok(reunionServiceImpl.saveReunion(reunionRequest));
    }

    @GetMapping("/Reunion/{reunionId}")
    public ResponseEntity<Reunion> getReunionDetails(@PathVariable int reunionId) {
        return ResponseEntity.ok(reunionServiceImpl.findReunionById(reunionId));
    }
    @GetMapping("/document/{documentId}")
    public ResponseEntity<Optional<files>> getDocumentsDetails(@PathVariable int documentId) {
        return ResponseEntity.ok(reunionServiceImpl.findDocumentById(documentId));
    }
    @GetMapping("/reunions/{fromDate}/{toDate}")
//    update to request param (path variabale)
    public ResponseEntity<List<Reunion>> getReunionsByDateRange(
            @PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @PathVariable("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        Optional<List<Reunion>> reunions = reunionServiceImpl.getReunionsByDateRange(fromDate, toDate);
        if (reunions.isPresent()) {
            return ResponseEntity.ok(reunions.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/reunions/{username}")
    public ResponseEntity<List<Reunion>> getReunionsByUsername(@PathVariable String username) {
        List<Reunion> reunions = reunionServiceImpl.getReunionsByUsername(username);
        return ResponseEntity.ok(reunions);
    }
    @DeleteMapping("reunion/{id}")
    public ResponseEntity<Void> deleteReunion(@PathVariable("id") int id) {
        reunionServiceImpl.deleteReunionById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
