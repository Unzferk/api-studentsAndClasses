package com.api.administration.models.mappers;

import com.api.administration.models.dtos.StudentDTO;
import com.api.administration.models.dtos.StudentSimpleDTO;
import com.api.administration.models.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {

    StudentMapper STUDENT_MAPPER = Mappers.getMapper(StudentMapper.class);

    StudentDTO toStudentDTO(Student student);

    Student toStudent(StudentDTO studentDTO);

    List<StudentDTO> toStudentDTO(List<Student> students);

    StudentSimpleDTO toStudentSimpleDTO(Student student);

    List<StudentSimpleDTO> toStudentSimpleDTO(List<Student> students);

}
