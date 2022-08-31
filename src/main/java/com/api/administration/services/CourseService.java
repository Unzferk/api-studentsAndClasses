package com.api.administration.services;

import com.api.administration.models.dtos.CourseDTO;
import com.api.administration.models.dtos.StudentSimpleDTO;
import com.api.administration.models.entities.Course;

import java.util.List;

public interface CourseService {
    CourseDTO getCourse(String code);

    List<CourseDTO> getCourses();

    CourseDTO postCourse(CourseDTO courseDTO);

    CourseDTO putCourse(String code, CourseDTO courseDTO);

    void deleteCourse(String code);

    List<StudentSimpleDTO> getCourseStudents(String code);

    Course addStudentToCourse(String code, String studentId);

    Course removeStudentFromCourse(String code, String studentId);
}
