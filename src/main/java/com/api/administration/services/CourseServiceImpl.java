package com.api.administration.services;

import com.api.administration.exceptions.ResourceAlreadyExistException;
import com.api.administration.exceptions.ResourceNotFoundException;
import com.api.administration.models.dtos.CourseDTO;
import com.api.administration.models.entities.Course;
import com.api.administration.models.mappers.CourseMapper;
import com.api.administration.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

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
    public CourseDTO putCourse(String code, Course course){
        Optional<Course> exist = courseRepository.findByCode(code);
        if(!exist.isPresent()){
            throw new ResourceNotFoundException(Course.class, code);
        }
        course.setUid(exist.get().getUid());
        courseRepository.save(course);
        return CourseMapper.COURSE_MAPPER.toCourseDTO(course);
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
}
