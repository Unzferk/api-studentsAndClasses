package com.api.administration.repositories;

import com.api.administration.models.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

    @Query("SELECT s FROM Student s WHERE s.active=true ORDER BY s.firstName")
    List<Student> findAllActivesOrderByName();

    Optional<Student> findByStudentId(String studentId);
}
