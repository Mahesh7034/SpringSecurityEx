package com.telusko.springboot.SpringSecurityDemo.service;

import com.telusko.springboot.SpringSecurityDemo.model.Student;
import com.telusko.springboot.SpringSecurityDemo.repo.Student_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    Student_repo studentRepo;


    public List<Student> getStudents() {
        return studentRepo.findAll();

    }


    public Student getStudent(int id) {
        Optional<Student> st =  studentRepo.findById(id);
        return st.orElse(null);
    }

    public Student addOrUpdate(Student student) throws IOException {

       return studentRepo.save(student);
    }

    public void delete(Student st) {


        studentRepo.delete(st);
    }
}
