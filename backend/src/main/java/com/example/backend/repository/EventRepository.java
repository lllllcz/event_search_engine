package com.example.backend.repository;
import com.example.backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
public interface EventRepository extends JpaRepository<Event,Integer>{
    @Query(value="select * from events a where a.themeid=:themeid",nativeQuery = true)
     List<Event>findEventByThemeid(int themeid);

    @Query(value="select * from events a where a.title=:title and a.content=:content and a.eventid=:id",nativeQuery = true)
     Event findEventByTitleAndContentAndId(String title,String content,Integer id);


}
