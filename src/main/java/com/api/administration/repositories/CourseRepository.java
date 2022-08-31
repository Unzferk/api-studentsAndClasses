package com.api.administration.repositories;

import com.api.administration.models.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {

    @Query("SELECT c FROM Course c WHERE c.active=true ORDER BY c.title")
    List<Course> findAllActivesOrderByTitle();

    Optional<Course> findByCode(String code);
}
