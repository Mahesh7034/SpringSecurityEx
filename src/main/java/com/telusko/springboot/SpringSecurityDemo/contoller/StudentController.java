package com.telusko.springboot.SpringSecurityDemo.contoller;

import com.telusko.springboot.SpringSecurityDemo.model.Student;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students;

    @PostConstruct
    public void init() {
        students = new ArrayList<>(List.of(
                new Student(1, "mahesh", 43),
                new Student(2, "bhopal", 98)
        ));
    }


    @GetMapping("/")
    public String getSession(HttpServletRequest req) {
        return "Your New Session Id : " + req.getSession().getId();

    }

    @GetMapping("/token")
    public CsrfToken getToken(HttpServletRequest req) {
        return (CsrfToken) req.getAttribute("_csrf");

    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;

    }




    @PostMapping("/student")
    public Student add(@RequestBody Student student) {
        students.add(student);
        return student;
    }
}
