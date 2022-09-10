package com.example.backend.service;
import com.example.backend.entity.*;
import java.util.List;
public interface CollectionService {
    List<Collection>findCollections(String username);
    void delete(Collection collection);
}
