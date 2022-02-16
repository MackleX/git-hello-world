package com.example.springtraining.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO extends RepresentationModel<StudentDTO> {
    private Long studentID;
    private String studentName;
    private String studentSchool;
    private boolean OK;
}
