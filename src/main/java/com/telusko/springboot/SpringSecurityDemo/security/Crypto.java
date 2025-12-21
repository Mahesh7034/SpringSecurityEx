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


    public static void main(String [] args) {

        //All Users
        List<AppUser> users  = userDetailsRepository.findAll();


        ArrayList<String> usernames  = new ArrayList<>();

        for(AppUser user : users) {
            usernames.add(user.getUsername());
            System.out.println(user.getUserpassword());
            user.setUserpassword(encoder.encode(user.getUsername()));
            userDetailsRepository.save(user);
        }


        List<AppUser> updatedUsers  = userDetailsRepository.findAll();



      for(int i  = 0; i < updatedUsers.size(); i++ ) {



            boolean isMatch = encoder.
                    matches(usernames.get(i), updatedUsers.get(i).getUserpassword());
          System.out.println("Does username "+ updatedUsers.get(i).getUsername()+  " match with password ? " + isMatch);


      }




    }

}
