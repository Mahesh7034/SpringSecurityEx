package com.telusko.springboot.SpringSecurityDemo.security;

import com.telusko.springboot.SpringSecurityDemo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringConfig {

    @Autowired
    MyUserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {


      return  http
         //csrf diable
                .csrf(customizer -> customizer.disable())


        //authenticate for every request put, post,delete, get
                .authorizeHttpRequests(request ->     request
                        .requestMatchers("/update-passwords-to-bcrypt")
                        .permitAll().anyRequest().authenticated())
        //login input enabling
                //.formLogin(Customizer.withDefaults())

                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;

    }







//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("mahesh")
//                .password("123")
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("Bhoom")
//                .password("123")
//                .roles("admin")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }



}
