package com.example.backend.serviceImpl;
import com.example.backend.dao.EventDao;
import com.example.backend.entity.Event;
import com.example.backend.result.Result;
import com.example.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventDao eventDao;
    @Override
    public void eventStore(List<Result> effectiveUrls,int themeid){
        eventDao.eventStore(effectiveUrls,themeid);
    }
    @Override
    public List<Event>findEventsbyThemeid(int themeid){
        return eventDao.findEventsbyThemeid(themeid);
    }
    @Override
    public void addToCollection(String title,String content,Integer id,String username){
        eventDao.addToCollection(title,content,id,username);
    }


}
