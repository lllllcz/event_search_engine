package com.example.backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "history")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class History {
    private int id;
    private Date time;
    private String theme;
    private String username;
    public History(){}
    public History(Date time,String theme,String username){
        this.time=time;
        this.theme=theme;
        this.username=username;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId(){return id;}
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name="theme")
    public String getTheme(){return theme;}
    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Basic
    @Column(name="username")
    public String getUsername(){return username;}
    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name="time")
    public Date getTime(){return time;}
    public void setTime(Date time){
        this.time=time;
    }


}
