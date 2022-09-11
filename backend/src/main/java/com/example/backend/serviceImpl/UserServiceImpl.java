package com.example.backend.serviceImpl;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.Utils.SessionUtil;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.dao.UserDao;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;

    @Override
    public String handleLogin(String username,String password){
       return   userDao.handleLogin(username,password);
    }

    @Override
    public String handleRegister(String username,String password){
        return userDao.handleRegister(username,password);
    }

    @Override
    public JSONObject checkSession() {
        JSONObject auth = SessionUtil.getAuth();

        JSONObject check = new JSONObject();

        if (auth == null) {
            check.put("status", -1);
            check.put("msg", "未登录");
        }
        else {
            check.put("status", 0);
            check.put("msg", "已登陆");
        }
        check.put("data", auth);

        return check;
    }



}
