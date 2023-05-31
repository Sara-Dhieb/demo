package com.myproject.BoardManagement.demo.service.impl;

import com.myproject.BoardManagement.demo.ReunionRequest;
import com.myproject.BoardManagement.demo.model.Document;
import com.myproject.BoardManagement.demo.model.Reunion;
import com.myproject.BoardManagement.demo.model.User;
import com.myproject.BoardManagement.demo.repository.DocumentRepository;
import com.myproject.BoardManagement.demo.repository.ReunionRepository;
import com.myproject.BoardManagement.demo.repository.UserRepository;
import com.myproject.BoardManagement.demo.service.ReunionService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class ReunionServiceImpl implements ReunionService {
    @Autowired
    ReunionRepository reunionRepository;
   @Autowired
    UserRepository userRepository;
    @Autowired
    DocumentRepository documentRepository;

    @Override
    public Reunion saveReunion (ReunionRequest reunionRequest) {
        List<User> userList = new ArrayList<>();
//        User userToAdd = null;
        if (reunionRequest.getUsers() != null && !reunionRequest.getUsers().isEmpty()) {
//            we defined user as an id in the ReunionRequest
            reunionRequest.getUsers().forEach(userId -> {
                Optional<User> userToAdd=(userRepository.findById(userId));
//                if userToAdd != null
                if (userToAdd.isPresent()) {

                    userList.add(userToAdd.get());
                }
                //todo handle exception
                else {
                    System.err.println("The variable is null");
                }
            });
        }

        List<Document> documents = new ArrayList<>();
        if (reunionRequest.getStringHashMap() != null && !reunionRequest.getStringHashMap().isEmpty()) {
//            we defined user as an id in the ReunionRequest
            reunionRequest.getStringHashMap().forEach((key,value) -> {
                byte[] fileContent = null;
                try {
                    fileContent = FileUtils.readFileToByteArray(new File(key));
                    Document document = Document.builder()
                            .name(value)
                            .fileContent(fileContent)
                            .date(new Date()).build();
                    Document savedDocument = documentRepository.save(document);
                    documents.add(savedDocument);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String encodedString = Base64.getEncoder().encodeToString(fileContent);
            });
        }

        Reunion reunion = Reunion.builder()
                .date(reunionRequest.getDate())
                .users(userList)
                .time(reunionRequest.getTime())
                .subject(reunionRequest.getSubject())
                .documents(documents)
                .build();

        return reunionRepository.save(reunion);
    }

    public Reunion findReunionById(int id){
        Optional<Reunion> reunion = reunionRepository.findById(id);
        if (reunion.isPresent()) {
            return reunion.get();
        }
        else
        return null;
    }
    public Optional<Document> findDocumentById(int id) {
        Optional<Document> document = documentRepository.findById(id);
        if (document.isPresent()) {
            return Optional.of(document.get());
        }
        else
            return null;
    }
    ///find reunion à partir d'une certaine date
    public Optional<List<Reunion>> getReunionsByDateRange(Date fromDate, Date toDate) {
        Optional<List<Reunion>> reunions =  reunionRepository.findByDateBetween(fromDate,toDate);
        return reunions.map(Optional::of).orElse(null);

    }
    public List<Reunion> getReunionsByUsername(String username) {
        return reunionRepository.findByUsersUsername(username);
    }
    }


    //Saving documents





