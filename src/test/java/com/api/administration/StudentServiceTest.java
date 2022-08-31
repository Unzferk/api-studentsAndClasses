package com.api.administration;

import com.api.administration.exceptions.ResourceAlreadyExistException;
import com.api.administration.exceptions.ResourceNotFoundException;
import com.api.administration.models.dtos.StudentDTO;
import com.api.administration.models.entities.Student;
import com.api.administration.models.mappers.StudentMapper;
import com.api.administration.repositories.StudentRepository;
import com.api.administration.services.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private String studentUid;
    private String studentId;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        studentUid = UUID.randomUUID().toString();
        studentId = "8055512";
        student = new Student();
        student.setUid(studentUid);
        student.setStudentId(studentId);
        student.setFirstName("Juan");
        student.setActive(true);
    }


    @Test
    public void it_should_returnStudentByStudentId(){
        when(studentRepository.findByStudentId(studentId)).thenReturn(Optional.of(student));

        assertEquals(student.getFirstName(),studentService.getStudent(studentId).getFirstName());
        assertEquals(student.getStudentId(),studentService.getStudent(studentId).getStudentId());
    }

    @Test
    public void it_should_returnException_when_getStudentNotFound(){
        Mockito.when(studentRepository.findByStudentId(student.getStudentId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            studentService.getStudent(studentId);
        });
    }

    @Test
    public void it_should_returnException_when_creatingStudentAlreadyExist(){

        StudentDTO studentDTO = StudentMapper.STUDENT_MAPPER.toStudentDTO(student);

        when(studentRepository.findByStudentId(studentId)).thenReturn(Optional.of(student));
        assertThrows(ResourceAlreadyExistException.class, () -> {
            studentService.postStudent(studentDTO);
        });
    }
    @Test
    public void it_should_createNewStudent(){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(studentId);
        studentDTO.setFirstName("es2");
        studentDTO.setActive(true);
        Student studentMapped = StudentMapper.STUDENT_MAPPER.toStudent(studentDTO);

        when(studentRepository.save(any())).thenReturn(studentMapped);

        StudentDTO res = studentService.postStudent(studentDTO);
        assertEquals(studentMapped.getStudentId(),res.getStudentId());
        assertEquals(studentMapped.getFirstName(),res.getFirstName());
    }

    @Test
    public void it_should_updateStudent(){
        Student update = new Student();
        update.setStudentId(studentId);
        update.setFirstName("es5");
        update.setActive(false);

        when(studentRepository.findByStudentId(studentId)).thenReturn(Optional.of(student));
        when(studentRepository.save(update)).thenReturn(update);

        StudentDTO res = studentService.putStudent(studentId,update);
        assertEquals(studentId,res.getStudentId());
        assertEquals(update.getFirstName(),res.getFirstName());
    }

    @Test
    public void it_should_returnException_when_updateStudentNotFound(){
        Student update = new Student();

        Mockito.when(studentRepository.findById(student.getUid())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            studentService.putStudent(studentUid,update);
        });
    }

    @Test
    public void it_should_deleteStudent(){
        when(studentRepository.findByStudentId(studentId)).thenReturn(Optional.of(student));
        when(studentRepository.save(any())).thenReturn(student);

        studentService.deleteStudent(studentId);
        verify(studentRepository,times(1)).save(student);
    }


}
