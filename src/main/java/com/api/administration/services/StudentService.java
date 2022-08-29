package com.api.administration.services;

import com.api.administration.models.dtos.StudentDTO;
import com.api.administration.models.entities.Student;

import java.util.List;

public interface StudentService {

    StudentDTO getStudent(String uid);

    List<StudentDTO> getStudents();

    StudentDTO postStudent(StudentDTO studentDTO);

    StudentDTO putStudent(String uid, Student student);

    void deleteStudent(String uid);

}
