package com.example.backend.DaoImpl;
import com.example.backend.dao.ThemeDao;
import com.example.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.backend.entity.*;

import java.util.List;
@Repository
public class ThemeDaoImpl implements ThemeDao {
    @Autowired
    private ThemeRepository themeRepository;
    @Override
    public List<Theme>findAll(){
        return themeRepository.findAll();
    }
    @Override
    public void createTheme(String theme){
        Theme theme1=new Theme(theme);
        themeRepository.save(theme1);
    }

    @Override
    public Theme findbyTheme(String theme){
        return themeRepository.findThemeByTheme(theme);
    }


}
