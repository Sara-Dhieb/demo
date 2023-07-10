package com.myproject.BoardManagement.demo.service;

import com.myproject.BoardManagement.demo.ReunionRequest;
import com.myproject.BoardManagement.demo.model.Reunion;
import com.myproject.BoardManagement.demo.model.files;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReunionService {
    Reunion saveReunion (ReunionRequest reunionRequest);
    Reunion findReunionById(int id);
    Optional<files> findDocumentById(int id);
    Optional<List<Reunion>> getReunionsByDateRange(Date fromDate, Date toDate);
    List<Reunion> getReunionsByUsername(String username);
    List<Reunion> findAllReunions();
    void deleteReunionById(int id);
    Reunion updateReunion(ReunionRequest reunionRequest, int reunionId);


}
