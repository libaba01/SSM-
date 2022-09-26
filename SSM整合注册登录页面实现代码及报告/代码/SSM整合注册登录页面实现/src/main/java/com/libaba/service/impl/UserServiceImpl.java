package com.libaba.service.impl;

import com.libaba.dao.UserDao;
import com.libaba.domain.User;
import com.libaba.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    //映入UserDao对象
    @Autowired
    private UserDao userDao;  //注入Dao层对象

    @Override
    public List<User> findAll() {
        System.out.println("Service业务层：查询所有账户...");
        return userDao.findAll();
    }

    @Override
    public void saveUser(User user) {
        System.out.println("Service业务层：保存帐户...");
        userDao.saveUser(user);
    }

    @Override
    public String GetUsername(String username) {
        return userDao.GetUsername(username);
    }

    @Override
    public String GetPassword(String username) {
        return userDao.GetPassword(username);
    }
}
