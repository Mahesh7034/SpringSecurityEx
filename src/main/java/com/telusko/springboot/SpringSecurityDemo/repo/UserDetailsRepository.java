package com.telusko.springboot.SpringSecurityDemo.repo;

import com.telusko.springboot.SpringSecurityDemo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<AppUser,Integer > {
    AppUser findByUsername(String username);
}
