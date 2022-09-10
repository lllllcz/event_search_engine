package com.example.backend.serviceImpl;
import com.example.backend.dao.ThemeDao;
import com.example.backend.entity.Theme;
import com.example.backend.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService {


    @Autowired
    private ThemeDao themeDao;

    @Override
    public List<Theme>findAll(){
        return  themeDao.findAll();
    }

    @Override
    public void createTheme(String theme){
        themeDao.createTheme(theme);
    }


    @Override
    public Theme findbyTheme(String theme){
        return themeDao.findbyTheme(theme);
    }




}
