package com.api.administration.services;

import com.api.administration.exceptions.ResourceNotFoundException;
import com.api.administration.models.dtos.StudentDTO;
import com.api.administration.models.entities.Student;
import com.api.administration.models.mappers.StudentMapper;
import com.api.administration.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDTO getStudent(String studentId){
        Optional<Student> Student = studentRepository.findByStudentId(studentId);
        if(!Student.isPresent()){
            throw new ResourceNotFoundException(Student.class, studentId);
        }
        return StudentMapper.STUDENT_MAPPER.toStudentDTO(Student.get());
    }

    @Override
    public List<StudentDTO> getStudents(){
        List<Student> students = studentRepository.findAllByOrderByFirstNameAsc();
        return StudentMapper.STUDENT_MAPPER.toStudentDTO(students);
    }

    @Override
    public StudentDTO postStudent(StudentDTO studentDTO){
        Student student = StudentMapper.STUDENT_MAPPER.toStudent(studentDTO);
        studentRepository.save(student);
        return studentDTO;
    }

    @Override
    public StudentDTO putStudent(String studentId, Student student){
        Optional<Student> exist = studentRepository.findByStudentId(studentId);
        if(!exist.isPresent()){
            throw new ResourceNotFoundException(Student.class, studentId);
        }
        student.setUid(exist.get().getUid());
        studentRepository.save(student);
        return StudentMapper.STUDENT_MAPPER.toStudentDTO(student);
    }

    @Override
    public void deleteStudent(String studentId){
        Optional<Student> exist = studentRepository.findByStudentId(studentId);
        if(!exist.isPresent()){
            throw new ResourceNotFoundException(Student.class, studentId);
        }
        exist.get().setActive(false);
        studentRepository.save(exist.get());
    }

}
