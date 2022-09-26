package com.libaba.controller;

import com.libaba.domain.User;
import com.libaba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    //注入Service对象
    @Autowired
    private UserService userService;

    //注册功能
    @RequestMapping("/testregister")
    public String UserRegister(String username, String password, String email, String telephone){
        //下面userService方法从数据库中查询结果
        List<User> list = userService.findAll();
        //将从前端传出来的数据存到数据库
        User user = new User(username, password, email, telephone);
        userService.saveUser(user);
        String username_frombehind=userService.GetUsername(username);
            if(username.equals("") && username.equals(username_frombehind)) {
                return "register_fail";
            }
            return "login";
    }

    //登录的入口，不设置无法访问，自动过滤静态资源
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //注册的入口，不设置无法访问，自动过滤静态资源
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    //登录功能
    @RequestMapping("/testlogin")
    public String UserLogin(String username, String password, HttpSession session,Integer id,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<User> list = userService.findAll();
        //共享数据
//        ModelAndView mov=new ModelAndView("redirect:/testlogin");
        //共享数据
//        session.setAttribute("username",username);
//        session.setAttribute("password",password);

        //获取数据库中的值
        String username_frombehind=userService.GetUsername(username);
        String password_frombehind=userService.GetPassword(username);

        if (username.equals(username_frombehind) && password.equals(password_frombehind)) {
            return "login_success";
        } else{
            return "login_fail";
        }


//            if (username_fromforward != username_frombehind) {//用户名不相同
//                request.getRequestDispatcher("/login_fail").forward(request, response);
//                break;
//            } else {
//                if (password_fromforward != password_frombehind) {//密码不相同
//                    request.getRequestDispatcher("/login_fail").forward(request, response);
//                    break;
//                }else {
//                    request.getRequestDispatcher("/login_success").forward(request, response);
//                    break;
//                }
//            }

    }

//    //登录失败
//    @RequestMapping("/testlogin1")
//    public void UserLogin1(String username, String password, HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//        List<User> list = userService.findAll();
//        //先定义从前端获取到的两个属性
//        String password_fromforward = String.valueOf(session.getAttribute("password"));
//        String username_fromforward = String.valueOf(session.getAttribute("username"));
//        Iterator it = list.iterator();
//        while (it.hasNext()) {
//            User user1 = (User) it.next();
//            String password_frombehind = user1.getPassword();
//            String username_frombehind = user1.getUsername();
//            if (username_fromforward != username_frombehind) {//用户名不相同
//                request.getRequestDispatcher("/login_fail").forward(request, response);
//            } else {
//                if (password_fromforward != password_frombehind) {//密码不相同
//                    request.getRequestDispatcher("/login_fail").forward(request, response);
//                }
//            }
//        }
//
//    }
}