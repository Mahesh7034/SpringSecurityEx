package com.telusko.springboot.SpringSecurityDemo.repo;

import com.telusko.springboot.SpringSecurityDemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Student_repo extends JpaRepository<Student, Integer> {
}
