package com.api.administration.controllers;

import com.api.administration.models.dtos.CourseDTO;
import com.api.administration.models.dtos.StudentDTO;
import com.api.administration.models.dtos.StudentSimpleDTO;
import com.api.administration.models.entities.Course;
import com.api.administration.models.entities.Student;
import com.api.administration.services.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    
    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping
    public List<CourseDTO> getCourses(){
        return courseService.getCourses();
    }

    @GetMapping("{code}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable String code){
        return new ResponseEntity<>(courseService.getCourse(code), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> postCourse(@RequestBody @Valid CourseDTO courseDTO){
        return new ResponseEntity<>(courseService.postCourse(courseDTO),HttpStatus.CREATED);
    }

    @PutMapping("{code}")
    public ResponseEntity<CourseDTO> putCourse(@PathVariable String code, @RequestBody CourseDTO courseDTO ){
        return new ResponseEntity<>(courseService.putCourse(code,courseDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{code}")
    public ResponseEntity<String> deleteCourse(@PathVariable String code){
        courseService.deleteCourse(code);
        return new ResponseEntity<>("Course "+code+" has been deleted",HttpStatus.OK);
    }

    @GetMapping("/{code}/student")
    public ResponseEntity<List<StudentSimpleDTO>> getCourseStudents(@PathVariable String code){
        return new ResponseEntity<>(courseService.getCourseStudents(code), HttpStatus.OK);
    }

    @PutMapping("/{courseCode}/student/{studentId}")
    public ResponseEntity<Course> addStudentToCourse(@PathVariable String courseCode, @PathVariable String studentId  ){
        return new ResponseEntity<>(courseService.addStudentToCourse(courseCode,studentId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{courseCode}/student/{studentId}")
    public ResponseEntity<Course> removeStudentFromCourse(@PathVariable String courseCode, @PathVariable String studentId  ){
        return new ResponseEntity<>(courseService.removeStudentFromCourse(courseCode,studentId), HttpStatus.ACCEPTED);
    }
}
