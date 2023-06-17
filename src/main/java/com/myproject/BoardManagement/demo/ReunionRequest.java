package com.myproject.BoardManagement.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myproject.BoardManagement.demo.model.files;
import com.myproject.BoardManagement.demo.model.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;

@Data
public class ReunionRequest {

    //createdAt and delete time
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;
//    @DateTimeFormat(pattern = "HH:mm")
    private String time;
    private String subject;
    private List<Integer> users;
    private List<files> files;
}
