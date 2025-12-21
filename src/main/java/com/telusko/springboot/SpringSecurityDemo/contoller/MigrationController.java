package com.telusko.springboot.SpringSecurityDemo.contoller;

import com.telusko.springboot.SpringSecurityDemo.model.AppUser;
import com.telusko.springboot.SpringSecurityDemo.repo.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setup")
public class MigrationController {

    @Autowired
    private UserDetailsRepository repo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @GetMapping("/migrate-passwords")
    public String migrate() {
        List<AppUser> users = repo.findAll();
        int count = 0;

        for (AppUser user : users) {


                user.setUserpassword(encoder.encode(user.getUsername()));
                repo.save(user);
                count++;

        }
        return "Migration successful! Encoded " + count + " users.";
    }
}