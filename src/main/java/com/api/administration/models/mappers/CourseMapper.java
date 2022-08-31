package com.api.administration.models.mappers;

import com.api.administration.models.dtos.CourseDTO;
import com.api.administration.models.dtos.CourseSimpleDTO;
import com.api.administration.models.entities.Course;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseMapper {

    CourseMapper COURSE_MAPPER = Mappers.getMapper(CourseMapper.class);

    CourseDTO toCourseDTO(Course course);
    Course toCourse(CourseDTO CourseDTO);

    List<CourseDTO> toCourseDTO(List<Course> courses);

    CourseSimpleDTO toCourseSimpleDTO(Course course);

    List<CourseSimpleDTO> toCourseSimpleDTO(List<Course> courses);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCourseFromDTO(CourseDTO courseDTO, @MappingTarget Course course);
}
