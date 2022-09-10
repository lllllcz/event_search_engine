package com.example.backend.DaoImpl;
import com.example.backend.dao.EventDao;
import com.example.backend.repository.*;
import com.example.backend.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.backend.entity.*;

import java.util.List;
@Repository
public class EventDaoImpl implements EventDao{
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CollectionRepository collectionRepository;


    @Override
    public void eventStore(List<Result> effectiveUrls,int themeid){
        int size=effectiveUrls.size();
        for(int i=0;i<size;i++){
            Result result=effectiveUrls.get(i);
            Event event=new Event(themeid,result.time,result.title,result.content,result.Url);
            eventRepository.save(event);
        }
    }


    @Override
    public List<Event>findEventsbyThemeid(int themeid){
        return  eventRepository.findEventByThemeid(themeid);
    }


    @Override
    public void addToCollection(String title,String content,Integer id,String username){
     Event event=eventRepository.findEventByTitleAndContentAndId(title,content,id);
     Collection collection=new Collection(event.getTheme(),event.getTime(),username,event.getTitle(),event.getContent(),event.getUrl());
     List<Collection>collections=collectionRepository.findCollectionsByUsername(username);
     int len=collections.size();
        for (int i = 0; i <len ; i++) {
            if(collections.get(i).isEqual(collection)){
                return;
            }
        }
     collectionRepository.save(collection);

    }

}
