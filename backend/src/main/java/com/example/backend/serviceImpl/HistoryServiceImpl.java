package com.example.backend.serviceImpl;
import com.example.backend.dao.*;
import com.example.backend.entity.*;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryDao historyDao;

    @Override
    public void addToHistory(String theme, String username, Date time){
        historyDao.addToHistory(theme,username,time);
    }

    @Override
    public List<History> showHistory(String username){
        return historyDao.showHistory(username);
    }

    @Override
    public void delete(String theme,String username,String time){
        historyDao.delete(theme,username,time);
    }
}
