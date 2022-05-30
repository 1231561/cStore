package com.hyh.cstore.service;

import com.hyh.cstore.IUserService;
import com.hyh.cstore.entity.User;
import com.hyh.cstore.ex.ServiceException;
import com.hyh.cstore.ex.UpdateException;
import com.hyh.cstore.ex.UserNotFoundException;
import com.hyh.cstore.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class ServiceTest {
    @Autowired
    private IUserService service;
    @Test
    void reg(){
        try {
            User user = new User();
            user.setUsername("李四");
            user.setPassword("123456");
            service.reg(user);
            System.out.println("注册成功");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    void login(){
        try {
            service.login("李四","123");
            System.out.println("登陆成功");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    void changePassword(){
        service.changePassword(3,"王五","123456","654321");
    }
    @Test
    public void changeInfoByUid() {
        User user = new User();
        user.setPhone("123123");
        user.setUid(2);
        user.setEmail("1321@qq.com");
        user.setGender(0);
        user.setModifiedUser("王五");
        user.setModifiedTime(new Date());


    }

    @Test
    public void findUserByUid() {
        User user = new User();
        user.setUid(2);
        System.out.println(service.findUserByUid(user.getUid()));
    }
    @Test
    public void updateAvatar() {
        service.updateAvatar(3,"/img/header002","小明");
    }

}
