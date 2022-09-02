package com.api.administration;

import com.api.administration.exceptions.ResourceAHasResourceBException;
import com.api.administration.exceptions.ResourceNotFoundException;
import com.api.administration.models.entities.Course;
import com.api.administration.models.entities.Student;
import com.api.administration.repositories.CourseRepository;
import com.api.administration.repositories.StudentRepository;
import com.api.administration.services.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CourseServiceTest {
    @Mock
    CourseRepository courseRepository;
    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    CourseServiceImpl courseService;

    private Course course;
    private String courseUid;
    private String courseCode;
    private Student student;
    private String studentUid;
    private String studentId;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        courseUid = UUID.randomUUID().toString();
        courseCode = "0000";
        course = new Course();
        course.setUid(courseUid);
        course.setCode(courseCode);
        course.setTitle("Title");
        course.setActive(true);
        course.setStudents(new ArrayList<>());

        studentUid = UUID.randomUUID().toString();
        studentId = "8055512";
        student = new Student();
        student.setUid(studentUid);
        student.setStudentId(studentId);
        student.setFirstName("Juan");
        student.setActive(true);
    }

    @Test
    public void it_should_addStudentToCourseSuccess(){

        when(studentRepository.findByStudentId(studentId)).thenReturn(Optional.of(student));
        when(courseRepository.findByCode(courseCode)).thenReturn(Optional.of(course));
        when(courseRepository.studentRegistered(studentId,courseCode)).thenReturn(false);
        when(courseRepository.save(course)).thenReturn(course);

        Course result = courseService.addStudentToCourse(courseCode,studentId);
        assertEquals(1,result.getStudents().size());
    }

    @Test
    public void it_should_return_exception_when_addStudentToCourse_studentNotFound(){

        when(studentRepository.findByStudentId(studentId)).thenReturn(Optional.empty());
        when(courseRepository.findByCode(courseCode)).thenReturn(Optional.of(course));
        assertThrows(ResourceNotFoundException.class, () -> {
            courseService.addStudentToCourse(courseCode,studentId);
        });
    }

    @Test
    public void it_should_return_exception_when_addStudentToCourse_courseNotFound(){

        when(studentRepository.findByStudentId(studentId)).thenReturn(Optional.of(student));
        when(courseRepository.findByCode(courseCode)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            courseService.addStudentToCourse(courseCode,studentId);
        });
    }

    @Test
    public void it_should_return_exception_when_addStudentToCourse_studentIsRegisteredIntoCourse(){

        when(studentRepository.findByStudentId(studentId)).thenReturn(Optional.of(student));
        when(courseRepository.findByCode(courseCode)).thenReturn(Optional.of(course));
        when(courseRepository.studentRegistered(studentId,courseCode)).thenReturn(true);
        assertThrows(ResourceAHasResourceBException.class, () -> {
            courseService.addStudentToCourse(courseCode,studentId);
        });
    }

    
}
