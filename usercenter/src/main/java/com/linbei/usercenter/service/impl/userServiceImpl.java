package com.linbei.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linbei.usercenter.mapper.userMapper;
import com.linbei.usercenter.model.domain.user;
import com.linbei.usercenter.service.userService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
* @author 14823
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2022-07-17 09:42:51
*/
@Service
public class userServiceImpl extends ServiceImpl<userMapper, user>
    implements userService{

    private static final String SALT = "linbei";

    public static final String USER_LOGIN_STATUS = "user_login_status";
    

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {

        //输入非空
        if(StringUtils.isAnyEmpty(userAccount,userPassword,checkPassword)){
            return -1;
        }

        //用户账号长度
        if(userAccount.length() < 6){
            return -1;
        }

        //用户密码和确认密码长度
        if(userPassword.length() < 8 | checkPassword.length() < 8){
            return -1;
        }

        //用户密码和确认密码是否相同
        if(!userPassword.equals(checkPassword)){
            return -1;
        }

        //用户账号是否合法
        String unvalidPattern = ".*[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*()——+|{}【】‘；：”“’。，、？\\\\]+.*";
        Matcher m = Pattern.compile(unvalidPattern).matcher(userAccount);
        if(m.find()){
            return -1;
        }

        //用户密码加密

        String md5Password = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        //用户是否重复
        QueryWrapper<user> qw = new QueryWrapper<>();
        qw.eq("userAccount", userAccount);
        long rs = this.count(qw);
        if(rs > 0){
            return -1;
        }

        //用户注册
        user u = new user();
        u.setUserAccount(userAccount);
        u.setUserPassword(md5Password);
        boolean registerResult = this.save(u); //插入到数据库
        if(!registerResult){
            return -1;
        }

        return u.getUserId();
    }

    @Override
    public user userLogin(String userAccount, String userPassword, HttpServletRequest request) {

        //输入非空
        if(StringUtils.isAnyEmpty(userAccount,userPassword)){
            return null;
        }

        //用户账号长度
        if(userAccount.length() < 6){
            return null;
        }

        //用户密码和确认密码长度
        if(userPassword.length() < 8){
            return null;
        }

        //用户账号是否合法
        String unvalidPattern = ".*[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*()——+|{}【】‘；：”“’。，、？\\\\]+.*";
        Matcher m = Pattern.compile(unvalidPattern).matcher(userAccount);
        if(m.find()){
            return null;
        }

        //用户密码加密

        String md5Password = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        //查询用户是否存在
        QueryWrapper<user> qw = new QueryWrapper<>();
        qw.eq("userAccount", userAccount);
        qw.eq("userPassword", md5Password);
        user rs = this.getOne(qw);
        user cleanUser = getCleanUser(rs);

        //记录用户状态
        request.getSession().setAttribute(USER_LOGIN_STATUS,cleanUser);

        return cleanUser;
    }

    @Override
    public List<user> userSearch(String userName, HttpServletRequest request) {
        if(!isAdmin(request)){
            return new ArrayList<>();
        }
        //查询用户
        List<user> rs = new ArrayList<>();
        QueryWrapper<user> qw = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(userName)){
            qw.like("userName", userName);
        }
        List<user> userList = this.list(qw);
        for (user user : userList) {
            rs.add(user);
        }
        return rs;
//        return userList.stream().map(user -> this.getCleanUser(user)).collect(Collectors.toList());
    }

    @Override
    public boolean userDelete(Integer userId, HttpServletRequest request) {
        if(isAdmin(request)){
            return false;
        }
        //删除用户
        QueryWrapper<user> qw = new QueryWrapper<>();
        qw.like("userId", userId);
        boolean rs = this.remove(qw);
        return rs;
    }

    @Override
    public user getCleanUser(user u) {
        if(u == null){
            return null;
        }
        //用户脱敏
        user cleanUser = new user();
        cleanUser.setUserId(u.getUserId());
        cleanUser.setUserName(u.getUserName());
        cleanUser.setUserAccount(u.getUserAccount());
        cleanUser.setUserImage(u.getUserImage());
        cleanUser.setUserGender(u.getUserGender());
        cleanUser.setUserPhone(u.getUserPhone());
        cleanUser.setUserMail(u.getUserMail());
        cleanUser.setIsVaild(u.getIsVaild());
        cleanUser.setCreateTime(u.getCreateTime());
        cleanUser.setUpdateTime(u.getUpdateTime());;
        cleanUser.setUserIdentity(u.getUserIdentity());

        return cleanUser;
    }

    @Override
    public void userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATUS);
    }

    public boolean isAdmin(HttpServletRequest request){
        //获取用户身份
        Object status = request.getSession().getAttribute(USER_LOGIN_STATUS);
        user u = (user) status;
        //判断是否为管理员
        return (u != null & u.getUserIdentity() == 1);
    }
}





