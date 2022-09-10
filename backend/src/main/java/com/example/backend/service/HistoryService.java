package com.example.backend.service;
import com.example.backend.entity.*;

import java.util.Date;
import java.util.List;
public interface HistoryService {
    void addToHistory(String theme, String username, Date time);
    List<History> showHistory(String username);
    void delete(String theme,String username,String time);

}
