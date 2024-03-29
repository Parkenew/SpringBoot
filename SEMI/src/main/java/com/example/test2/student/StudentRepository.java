package com.example.test2.student;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    Page<Student> findAll(Pageable pageable);
    @Query("select distinct s from Student s where s.StdNo like '%:kd%'")
    Page<Student> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
    Page<Student> findAll(Specification<Student> spec,Pageable pageable);

}
