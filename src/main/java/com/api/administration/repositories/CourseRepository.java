package com.api.administration.repositories;

import com.api.administration.models.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {
    List<Course> findAllByOrderByTitleAsc();
    Optional<Course> findByCode(String code);
}
