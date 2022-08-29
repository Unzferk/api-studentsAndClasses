package com.api.administration.controllers;

import com.api.administration.models.dtos.CourseDTO;
import com.api.administration.models.entities.Course;
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
    public ResponseEntity<CourseDTO> putCourse(@PathVariable String code, @RequestBody @Valid Course course ){
        return new ResponseEntity<>(courseService.putCourse(code,course), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{code}")
    public ResponseEntity<String> deleteCourse(@PathVariable String code){
        courseService.deleteCourse(code);
        return new ResponseEntity<>("Course "+code+" has been deleted",HttpStatus.OK);
    }
}
