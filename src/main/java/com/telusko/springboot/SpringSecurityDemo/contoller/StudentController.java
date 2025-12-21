package com.telusko.springboot.SpringSecurityDemo.contoller;

import com.telusko.springboot.SpringSecurityDemo.model.AppUser;
import com.telusko.springboot.SpringSecurityDemo.model.Student;
import com.telusko.springboot.SpringSecurityDemo.repo.UserDetailsRepository;
import com.telusko.springboot.SpringSecurityDemo.service.StudentService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    UserDetailsRepository userDetailsRepository;


    @Autowired
    StudentService studentService;





    @GetMapping("/")
    public String getSession(HttpServletRequest req) {
        return "Your New Session Id : " + req.getSession().getId();

    }


    // Password Encoder with Strength 12 to encrypt all the passwords in DB
    @GetMapping("/update-passwords-to-bcrypt")
    @Transactional
    public String updateAllPasswordsToBcrypt() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        List<AppUser> users = userDetailsRepository.findAll();

        for (AppUser user : users) {
            String plainPassword = user.getUserpassword(); // Save current plain password
            String hashedPassword = encoder.encode(plainPassword);
            user.setUserpassword(hashedPassword); // Update with hash

            userDetailsRepository.save(user); // Save to database
        }

        return "Updated " + users.size() + " passwords to BCrypt!";
    }


    @GetMapping("/token")
    public CsrfToken getToken(HttpServletRequest req) {
        return (CsrfToken) req.getAttribute("_csrf");

    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {

        List<Student> students = studentService.getStudents();
        if (students == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(students, HttpStatus.OK);

    }


    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student st = studentService.getStudent(id);
        if (st == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.FOUND);
    }


    @PostMapping("/student")
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student st;
        try {
            st = studentService.addOrUpdate(student);
            return new ResponseEntity<>(st, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/student")
    public ResponseEntity<Student> modify(@RequestBody Student student) {
        Student st;
        try {
            st = studentService.addOrUpdate(student);
            return new ResponseEntity<>(st, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Student> delete(@PathVariable int id) {
        Student st;

        st = studentService.getStudent(id);
        if (st != null) {
            studentService.delete(st);
            return new ResponseEntity<>(st, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

}
