package com.example.backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class User {
    private int userid;
    private String username;
    private String password;
    public User(){}
    public User(String username,String password){
        this.username=username;
        this.password=password;
    }
    @Id
    @Column(name="userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return userid; }
    public void setId(int id) { this.userid = id; }

    @Basic
    @Column(name="username")
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @Basic
    @Column(name="password")
    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}


}

