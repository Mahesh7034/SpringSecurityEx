package com.telusko.springboot.SpringSecurityDemo.service;

import com.telusko.springboot.SpringSecurityDemo.model.AppUser;
import com.telusko.springboot.SpringSecurityDemo.repo.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDetailsRepository repo;
    private final BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder(12);


    public AppUser saveUser(AppUser user) {

        user.setUserpassword(encoder.encode(user.getUserpassword()));

        return repo.save(user);
    }
}
