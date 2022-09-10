package com.example.backend.serviceImpl;
import com.example.backend.dao.CollectionDao;
import com.example.backend.entity.Collection;
import com.example.backend.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionDao collectionDao;

    @Override
    public List<Collection> findCollections(String username){
        return collectionDao.findCollections(username);
    }

    @Override
    public void delete(Collection collection){
        collectionDao.delete(collection);
    }


}
