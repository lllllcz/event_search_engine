package com.example.backend.repository;
import com.example.backend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
public interface CollectionRepository extends JpaRepository<Collection,Integer>{
    @Query(value="select * from collections a where a.username=:username",nativeQuery = true)
    List<Collection>findCollectionsByUsername(String username);


}
