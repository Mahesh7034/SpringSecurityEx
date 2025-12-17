package com.telusko.springboot.SpringSecurityDemo.service;

import com.telusko.springboot.SpringSecurityDemo.model.AppUser;
import com.telusko.springboot.SpringSecurityDemo.model.UserPrincipal;
import com.telusko.springboot.SpringSecurityDemo.repo.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDetailsRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       AppUser user = repository.findByUsername(username);

       if(user ==  null) {
           System.out.println("USER NOT FOUND");
           throw new UsernameNotFoundException("USER NOT FOUND");
       }

        return new UserPrincipal(user);
    }
}
