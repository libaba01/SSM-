package com.libaba.Test;

import com.libaba.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test1 {
    @Test
    public void test1(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService=(UserService)context.getBean("userService",UserService.class);
        userService.findAll();
    }
}
