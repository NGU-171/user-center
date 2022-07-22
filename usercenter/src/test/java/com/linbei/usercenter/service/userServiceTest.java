package com.linbei.usercenter.service;
import java.util.Date;

import com.linbei.usercenter.model.domain.user;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class userServiceTest {

    @Resource
    private userService userservice;

    @Test
    void testAddUser(){
        user u = new user();

        u.setUserName("NGU");
        u.setUserAccount("123456");
        u.setUserImage("http://p4.music.126.net/Ms5TSpBr6tht8YreIgfG1Q==/109951167493624776.jpg?param=200y200");
        u.setUserGender(0);
        u.setUserPhone("123456");
        u.setUserPassword("123456789");
        u.setUserMail("123456");

        boolean rs = userservice.save(u);
        System.out.println(u.getUserId());
        Assertions.assertTrue(rs);

    }

    @Test
    void userRegister() {
        String userAccount = "19854829999";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        userservice.userRegister(userAccount,userPassword,checkPassword);
    }
}