package com.libaba.dao;

import com.libaba.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  //持久层
public interface UserDao {
    //输入返回
    @Select("select * from t_login")
    public List<User> findAll();

    //返回输出
    @Insert("insert into t_login(username,password,email,telephone) values(#{username},#{password},#{email},#{telephone})")
    public void saveUser(User user);

    //从数据库中返回用户名和密码信息
    @Select("select username from t_login where username= #{username1}")
    public String GetUsername(String username1);

    @Select("select password from t_login where username= #{username2}")
    public String GetPassword(String username2);

}
