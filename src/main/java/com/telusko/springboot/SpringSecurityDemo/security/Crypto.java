package com.telusko.springboot.SpringSecurityDemo.security;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.telusko.springboot.SpringSecurityDemo.model.AppUser;
import com.telusko.springboot.SpringSecurityDemo.repo.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class Crypto {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private static UserDetailsRepository userDetailsRepository;


    public static void main(String[] args) {

        //All Users

        boolean isMatch = encoder.
                matches("your guessing password", " encoded password");
        System.out.println("Does  match with password ? " + isMatch);

    }
}
