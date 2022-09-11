package com.example.backend.service;

import com.alibaba.fastjson.JSONObject;

public interface UserService {

    String handleLogin(String username,String password);
    String handleRegister(String username,String password);
    JSONObject checkSession();

}