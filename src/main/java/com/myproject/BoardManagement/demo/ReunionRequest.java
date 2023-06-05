package com.myproject.BoardManagement.demo;

import com.myproject.BoardManagement.demo.model.Document;
import com.myproject.BoardManagement.demo.model.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;

@Data
public class ReunionRequest {

    //createdAt and delete time
    private Date date;
    private Time time;
    private String subject;
    private List<Integer> users;
    private MultipartFile[] files;
}
