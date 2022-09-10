package com.example.backend.dao;
import com.example.backend.entity.Collection;

import java.util.List;
public interface CollectionDao {
    List<Collection>findCollections(String username);
    void delete(Collection collection);
}
