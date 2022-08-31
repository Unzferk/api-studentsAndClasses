package com.api.administration.services;

import com.api.administration.exceptions.ResourceAHasntResourceBException;
import com.api.administration.exceptions.ResourceAlreadyExistException;
import com.api.administration.exceptions.ResourceNotFoundException;
import com.api.administration.exceptions.ResourceAHasResourceBException;
import com.api.administration.models.dtos.CourseDTO;
import com.api.administration.models.dtos.StudentSimpleDTO;
import com.api.administration.models.entities.Course;
import com.api.administration.models.entities.Student;
import com.api.administration.models.mappers.CourseMapper;
import com.api.administration.models.mappers.StudentMapper;
import com.api.administration.repositories.CourseRepository;
import com.api.administration.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public CourseDTO getCourse(String code){
        Optional<Course> Course = courseRepository.findByCode(code);
        if(!Course.isPresent()){
            throw new ResourceNotFoundException(Course.class, code);
        }
        return CourseMapper.COURSE_MAPPER.toCourseDTO(Course.get());
    }

    @Override
    public List<CourseDTO> getCourses(){
        List<Course> courses = courseRepository.findAllActivesOrderByTitle();
        return CourseMapper.COURSE_MAPPER.toCourseDTO(courses);
    }

    @Override
    public CourseDTO postCourse(CourseDTO courseDTO){
        Optional<Course> Course = courseRepository.findByCode(courseDTO.getCode());
        if(Course.isPresent()){
            throw new ResourceAlreadyExistException(Course.class, courseDTO.getCode());
        }
        Course course = CourseMapper.COURSE_MAPPER.toCourse(courseDTO);
        course.setActive(true);
        courseRepository.save(course);
        return courseDTO;
    }

    @Override
    public CourseDTO putCourse(String code, CourseDTO courseDTO){
        Optional<Course> exist = courseRepository.findByCode(code);
        if(!exist.isPresent()){
            throw new ResourceNotFoundException(Course.class, code);
        }
        CourseMapper.COURSE_MAPPER.updateCourseFromDTO(courseDTO,exist.get());
        courseRepository.save(exist.get());
        return CourseMapper.COURSE_MAPPER.toCourseDTO(exist.get());
    }

    @Override
    public void deleteCourse(String code){
        Optional<Course> exist = courseRepository.findByCode(code);
        if(!exist.isPresent()){
            throw new ResourceNotFoundException(Course.class, code);
        }
        exist.get().setActive(false);
        courseRepository.save(exist.get());
    }

    @Override
    public List<StudentSimpleDTO> getCourseStudents(String code) {
        Optional<Course> course = courseRepository.findByCode(code);
        if(!course.isPresent()){
            throw new ResourceNotFoundException(Course.class, code);
        }
        List<StudentSimpleDTO> studentDTOS = StudentMapper.STUDENT_MAPPER.toStudentSimpleDTO(course.get().getStudents());
        return studentDTOS;
    }

    @Override
    public Course addStudentToCourse(String code, String studentId) {
        Optional<Course> course = courseRepository.findByCode(code);
        Optional<Student> student = studentRepository.findByStudentId(studentId);

        if(!course.isPresent()) throw new ResourceNotFoundException(Course.class, code);
        if(!student.isPresent()) throw new ResourceNotFoundException(Student.class, code);
        if(courseRepository.studentRegistered(studentId,code)) throw new ResourceAHasResourceBException(studentId, code);

        course.get().getStudents().add(student.get());
        return courseRepository.save(course.get());
    }

    @Override
    public Course removeStudentFromCourse(String code, String studentId) {
        Optional<Course> course = courseRepository.findByCode(code);
        Optional<Student> student = studentRepository.findByStudentId(studentId);

        if(!course.isPresent()) throw new ResourceNotFoundException(Course.class, code);
        if(!student.isPresent()) throw new ResourceNotFoundException(Student.class, code);
        if(!courseRepository.studentRegistered(studentId,code)) throw new ResourceAHasntResourceBException(studentId, code);

        course.get().getStudents().remove(student.get());
        return courseRepository.save(course.get());
    }
}
