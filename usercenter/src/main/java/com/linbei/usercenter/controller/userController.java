package com.linbei.usercenter.controller;


import com.linbei.usercenter.common.BaseResponse;
import com.linbei.usercenter.common.ResultUtils;
import com.linbei.usercenter.model.domain.user;
import com.linbei.usercenter.model.request.userLoginRequest;
import com.linbei.usercenter.model.request.userRegistRequest;
import com.linbei.usercenter.service.userService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.linbei.usercenter.service.impl.userServiceImpl.USER_LOGIN_STATUS;

/**
 * 用户接口
 *
 */
@RestController
@RequestMapping("/user")
public class userController {

    @Resource
    private userService userservice;

    @PostMapping("/register")
    public long userRegister(@RequestBody userRegistRequest urr){
        if(urr == null){
            return -1;
        }
        String userAccount = urr.getUserAccount();
        String userPassword = urr.getUserPassword();
        String checkPassword = urr.getCheckPassword();
        if(StringUtils.isAnyEmpty(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        long id = userservice.userRegister(userAccount, userPassword, checkPassword);
        return id;
    }

    @PostMapping("/login")
    public user userLogin(@RequestBody userLoginRequest ulr, HttpServletRequest request){
        if(ulr == null){
            return null;
        }
        String userAccount = ulr.getUserAccount();
        String userPassword = ulr.getUserPassword();
        if(StringUtils.isAnyEmpty(userAccount, userPassword)) {
            return null;
        }
        user rs = userservice.userLogin(userAccount, userPassword, request);
        return rs;
    }

    @GetMapping("/search")
    public List<user> userSearch(String userName, HttpServletRequest request){
        return userservice.userSearch(userName,request);
    }

    @PostMapping("/delete")
    public boolean userDelete(Integer userId, HttpServletRequest request){
        if(userId <= 0){
            return false;
        }
        return userservice.userDelete(userId, request);
    }

    @GetMapping("/current")
    public user userCurrent(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATUS);
        user u = (user) userObj;
        if(u == null){
            return null;
        }
        int currentId = u.getUserId();
        user cu = userservice.getById(currentId);
        user cleanUser = userservice.getCleanUser(cu);
        return cleanUser;
    }

    @PostMapping("/logout")
    public void userLogout(HttpServletRequest request){
        if(request == null){
            return;
        }
        userservice.userLogout(request);
    }
}
