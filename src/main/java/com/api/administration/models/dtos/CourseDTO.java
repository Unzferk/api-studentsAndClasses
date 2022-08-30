package com.api.administration.models.dtos;

import com.api.administration.models.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    @NotNull
    private String code;

    @NotNull
    @NotBlank
    private String title;

    private String description;

    private boolean active;

    List<Student> students;
}
