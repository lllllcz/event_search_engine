package com.example.backend.DaoImpl;
import com.example.backend.dao.CollectionDao;
import com.example.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.backend.entity.*;

import java.util.List;
@Repository
public class CollectionDaoImpl implements CollectionDao{
    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    public List<Collection> findCollections(String username){
      return collectionRepository.findCollectionsByUsername(username);
    }

    @Override
    public void delete(Collection collection){
        collectionRepository.delete(collection);
    }

}
