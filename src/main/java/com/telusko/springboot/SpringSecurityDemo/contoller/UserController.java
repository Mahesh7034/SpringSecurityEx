package com.telusko.springboot.SpringSecurityDemo.contoller;

import com.telusko.springboot.SpringSecurityDemo.model.AppUser;
import com.telusko.springboot.SpringSecurityDemo.service.JwtService;
import com.telusko.springboot.SpringSecurityDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService service;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("register")
    public AppUser register(@RequestBody AppUser user) {
        return service.saveUser(user);
    }

    @PutMapping("encrypt")
    public AppUser encrypt(@RequestBody AppUser user) {
        return service.saveUser(user);
    }



    @PostMapping("login")
    public String login(@RequestBody AppUser user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getUserpassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }else {
           return  "Login Failed";
        }
    }



}
