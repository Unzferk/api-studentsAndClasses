package com.api.administration.services;

import com.api.administration.models.dtos.CourseDTO;
import com.api.administration.models.entities.Course;

import java.util.List;

public interface CourseService {
    CourseDTO getCourse(String uid);

    List<CourseDTO> getCourses();

    CourseDTO postCourse(CourseDTO courseDTO);

    CourseDTO putCourse(String uid, Course course);

    void deleteCourse(String uid);
}
