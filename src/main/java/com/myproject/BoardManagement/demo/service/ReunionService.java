package com.myproject.BoardManagement.demo.service;

import com.myproject.BoardManagement.demo.ReunionRequest;
import com.myproject.BoardManagement.demo.model.Document;
import com.myproject.BoardManagement.demo.model.Reunion;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReunionService {
    Reunion saveReunion(ReunionRequest reunionRequest);
    Reunion findReunionById(int id);
    Optional<Document> findDocumentById(int id);
    Optional<List<Reunion>> getReunionsByDateRange(Date fromDate, Date toDate);
    List<Reunion> getReunionsByUsername(String username);

}
