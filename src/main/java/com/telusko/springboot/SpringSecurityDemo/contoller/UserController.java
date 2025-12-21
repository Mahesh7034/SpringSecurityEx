package com.telusko.springboot.SpringSecurityDemo.contoller;

import com.telusko.springboot.SpringSecurityDemo.model.AppUser;
import com.telusko.springboot.SpringSecurityDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("register")
    public AppUser register(@RequestBody AppUser user) {
        return service.saveUser(user);
    }

    @PutMapping("encrypt")
    public AppUser encrypt(@RequestBody AppUser user) {
        return service.saveUser(user);
    }


}
