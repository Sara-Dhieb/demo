package com.myproject.BoardManagement.demo.dto;

import com.myproject.BoardManagement.demo.ReunionRequest;
import com.myproject.BoardManagement.demo.model.files;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeetingDTO {
    private ReunionRequest meeting;
    private List<files> files;
}
