package com.libaba.Test;

import com.libaba.dao.UserDao;
import com.libaba.domain.User;
import com.libaba.util.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class test_mybatis {
    @Test
    public void run1() throws IOException {
//        User user =new User();
//        user.setUsername("杜永蓝");
//        user.setPassword("123456");
//        user.setEmail("@123QQ.COM");
//        user.setTelephone("1269952");
//        //将上述的信息全部存放在user对象中
//        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
//        UserDao userDao=sqlSession.getMapper(UserDao.class);
//        userDao.saveUser(user);
    }

    @Test
    public void run2() throws Exception {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        UserDao userDao=sqlSession.getMapper(UserDao.class);
        List<User> list = userDao.findAll();//将从数据库中查询到的结果全存放在这个list集合中
        for (User user: list ) {
            System.out.println(user);
        }
    }
}
