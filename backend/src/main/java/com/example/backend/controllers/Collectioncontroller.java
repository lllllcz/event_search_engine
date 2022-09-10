package com.example.backend.controllers;
import com.example.backend.entity.*;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

@CrossOrigin
@RestController
public class Collectioncontroller {
    @Autowired
    private EventService eventService;

    @Autowired
    private CollectionService collectionService;


    @RequestMapping(value = "/addToCollection/{title}/{content}/{username}/{id}")
    @ResponseBody
    public void handleAddtoCollection(@PathVariable String title,@PathVariable String content,@PathVariable Integer id,@PathVariable String username){
        System.out.println(title);
       eventService.addToCollection(title,content,id,username);
    }

    @RequestMapping(value = "/showCollection/{username}")
    @ResponseBody
    public JSONArray handleShowCollection(@PathVariable String username){
      List<Collection>collections=collectionService.findCollections(username);
        int len=collections.size();
        JSONArray array=new JSONArray();
        for(int i=len-1;i>=0;i--){
            JSONArray array1=new JSONArray();
            Collection collection =collections.get(i);
            array1.add(collection.getTitle());
            array1.add(collection.getContent());
            array1.add(collection.getUrl());
            array1.add(collection.getTime());
            array.add(array1);
        }
        // System.out.println(array);
        return array;
    }

    @RequestMapping(value = "/deleteCollection/{title}/{username}")
    @ResponseBody
    public void handleDeleteCollection(@PathVariable String title,@PathVariable String username){
        List<Collection>collections=collectionService.findCollections(username);
        int len= collections.size();
        for(int i=0;i<len;i++){
            if(collections.get(i).getTitle().equals(title)){
                collectionService.delete(collections.get(i));
                break;
            }
        }
    }
}
