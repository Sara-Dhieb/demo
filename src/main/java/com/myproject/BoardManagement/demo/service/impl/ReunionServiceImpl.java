package com.myproject.BoardManagement.demo.service.impl;

import com.myproject.BoardManagement.demo.ReunionRequest;
import com.myproject.BoardManagement.demo.model.files;
import com.myproject.BoardManagement.demo.model.Reunion;
import com.myproject.BoardManagement.demo.model.User;
import com.myproject.BoardManagement.demo.repository.filesRepository;
import com.myproject.BoardManagement.demo.repository.ReunionRepository;
import com.myproject.BoardManagement.demo.repository.UserRepository;
import com.myproject.BoardManagement.demo.service.ReunionService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

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
    filesRepository filesRepository;

    @Override
    public Reunion saveReunion ( @ModelAttribute("reunionRequest")ReunionRequest reunionRequest) {
        List<User> userList = new ArrayList<>();
//        User userToAdd = null;
        if (reunionRequest.getUsers() != null && !reunionRequest.getUsers().isEmpty()) {
//            we defined user as an id in the ReunionRequest
            reunionRequest.getUsers().forEach(userId -> {
                Optional<User> userToAdd = (userRepository.findById(userId));
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

        List<files> documents = new ArrayList<>();

        if (reunionRequest.getFiles() != null && !reunionRequest.getFiles().isEmpty()) {
//            we defined user as an id in the ReunionRequest
//            documents.addAll(files);
            for (files document:reunionRequest.getFiles() ){

                         document = files.builder()
                        .name(document.getName())
                        .fileContent(document.getFileContent())
                        .fileType(document.getFileType())
                        .date(new Date()).build();
                        files savedDocument = filesRepository.save(document);
                        documents.add(savedDocument);
            }

//            for (MultipartFile file : files) {
//                String fileName = file.getOriginalFilename();
//                String fileType = file.getContentType();
//                byte[] fileContent;
//                try {
//                    fileContent = file.getBytes();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                Document document = Document.builder()
//                        .name(fileName)
//                        .fileContent(fileContent)
//                        .fileType(fileType)
//                        .date(new Date()).build();
//                Document savedDocument = documentRepository.save(document);
//                documents.add(savedDocument);
//            }
        }
        Reunion reunion = Reunion.builder()
                .date(reunionRequest.getDate())
                .users(userList)
                .time(reunionRequest.getTime())
                .subject(reunionRequest.getSubject())
                .files(documents)
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
    public Optional<files> findDocumentById(int id) {
        Optional<files> document = filesRepository.findById(id);
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
    @Override
    public List<Reunion> findAllReunions() {
        return reunionRepository.findAll();


    }
    @Override
    public void deleteReunionById(int id) {
        reunionRepository.deleteById(id);
    }
    }



    //Saving documents





