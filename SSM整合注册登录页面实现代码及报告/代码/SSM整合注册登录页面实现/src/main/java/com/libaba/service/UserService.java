package com.libaba.service;

import com.libaba.domain.User;

import java.util.List;

public interface UserService {
    //查询
    public List<User> findAll();

    //保存
    public void saveUser(User user);

    //根据id从dao层获取username
    public String GetUsername(String username);

    //根据id从dao层获取password
    public String GetPassword(String username);
}
