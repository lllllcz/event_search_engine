package com.example.backend.service;
import com.example.backend.entity.Theme;

import java.util.List;
public interface ThemeService {
    List<Theme>findAll();
    void createTheme(String theme);
    Theme findbyTheme(String theme);

}
