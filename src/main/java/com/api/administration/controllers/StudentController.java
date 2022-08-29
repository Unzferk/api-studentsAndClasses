package com.api.administration.controllers;

import com.api.administration.models.dtos.StudentDTO;
import com.api.administration.models.entities.Student;
import com.api.administration.models.mappers.StudentMapper;
import com.api.administration.services.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping
    public List<StudentDTO> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping("{studentId}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable String studentId){
        return new ResponseEntity<>(studentService.getStudent(studentId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> postStudent(@RequestBody @Valid StudentDTO studentDTO){
        return new ResponseEntity<>(studentService.postStudent(studentDTO),HttpStatus.CREATED);
    }

    @PutMapping("{studentId}")
    public ResponseEntity<StudentDTO> putStudent(@PathVariable String studentId, @RequestBody @Valid Student student ){
        return new ResponseEntity<>(studentService.putStudent(studentId,student), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable String studentId){
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>("Student "+studentId+" has been deleted",HttpStatus.OK);
    }
}
