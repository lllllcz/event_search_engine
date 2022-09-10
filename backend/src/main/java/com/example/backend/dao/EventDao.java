package com.example.backend.dao;

import com.example.backend.entity.Event;
import com.example.backend.result.Result;

import java.util.List;

public interface EventDao {
 void eventStore(List<Result> effectiveUrls,int themeid);
 List<Event>findEventsbyThemeid(int themeid);
 void addToCollection(String title,String content,Integer id,String username);
}
