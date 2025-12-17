package com.telusko.springboot.SpringSecurityDemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "appusers")
public class AppUser {

    public AppUser(){}

    @Id
    private int id;
    private String username;
    private String userpassword;

    public AppUser(int id, String username, String userpassword) {
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                '}';
    }
}
