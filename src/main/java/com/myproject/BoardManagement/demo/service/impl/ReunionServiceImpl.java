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
    @Override
    public Reunion updateReunion(ReunionRequest reunionRequest, int reunionId) {
        Optional<Reunion> existingReunion = reunionRepository.findById(reunionId);

        if (existingReunion.isPresent()) {
            Reunion reunion = existingReunion.get();

            // Fetch the corresponding User entities using the provided user IDs
            List<User> users = new ArrayList<>();
            for (int userId : reunionRequest.getUsers()) {
                User user = findUserById(userId);
                if (user != null) {
                    users.add(user);
                } else {
                    System.err.println("The variable is null");
                }
            }
            List<files> documents = new ArrayList<>();

            if (reunionRequest.getFiles() != null && !reunionRequest.getFiles().isEmpty()) {
            reunion.getFiles().clear();
                for (files document:reunionRequest.getFiles() ){

                    document = files.builder()
                            .name(document.getName())
                            .fileContent(document.getFileContent())
                            .fileType(document.getFileType())
                            .date(new Date()).build();
                    files savedDocument = filesRepository.save(document);
                    documents.add(savedDocument);
                }

            }

            // Update the existing reunion with the data from the request
            reunion.setDate(reunionRequest.getDate());
            reunion.setTime(reunionRequest.getTime());
            reunion.setSubject(reunionRequest.getSubject());
            reunion.setUsers(users); // Set the list of User entities
            reunion.setFiles(documents);

            // Save the updated reunion back to the database
            return reunionRepository.save(reunion);
        } else {
            // If the reunion with the given ID doesn't exist, you can either throw an exception or handle it as needed.
            // For now, I'll return null in case the reunion doesn't exist.
            return null;
        }
    }


    // Helper method to find a User entity by its ID
    private User findUserById(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
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





