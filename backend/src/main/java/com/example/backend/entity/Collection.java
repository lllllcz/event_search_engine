package com.example.backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "collections")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Collection {
    private int eventid;
    private int themeid;
    private Date time;
    private String username;
    private String title;
    private String content;
    private String url;
    public Collection(){}
    public Collection(int themeid,Date time,String username,String title,String content,String url){
        this.themeid=themeid;
        this.username=username;
        this.time=time;
        this.title=title;
        this.content=content;
        this.url=url;
    }
    @Id
    @Column(name="eventid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getEventid(){return eventid;}
    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    @Basic
    @Column(name="username")
    public String getUsername(){return username;}
    public void setUsername(String username) {
        this.username = username;
    }


    @Basic
    @Column(name="themeid")
    public int getTheme(){return themeid;}
    public void setTheme(int themeid) {
        this.themeid = themeid;
    }

    @Basic
    @Column(name="time")
    public Date getTime(){return time;}
    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name="title")
    public String getTitle(){return title;}
    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name="content")
    public String getContent(){return content;}
    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name="url")
    public String getUrl(){return url;}
    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isEqual(Collection collection){
        if(collection.getTime().toString().equals(this.time.toString())&&collection.getTitle().equals(this.title)&&collection.getContent().equals(this.content)&&
        collection.getUrl().equals(this.url)){
            return true;
        }
        else
            return false;
    }
}

