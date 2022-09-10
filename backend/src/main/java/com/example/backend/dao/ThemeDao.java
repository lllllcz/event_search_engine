package com.example.backend.dao;

import com.example.backend.entity.Theme;
import java.util.List;
public interface ThemeDao {
    List<Theme>findAll();
    void createTheme(String theme);
    Theme findbyTheme(String theme);
}
