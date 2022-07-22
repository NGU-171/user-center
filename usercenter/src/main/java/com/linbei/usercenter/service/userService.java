package com.linbei.usercenter.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.linbei.usercenter.model.domain.user;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 14823
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2022-07-17 09:42:51
*/
public interface userService extends IService<user> {
    /**
     * 用户登录
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 确认密码
     * @return 用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户注册
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return 用户信息
     */
    user userLogin(String userAccount, String userPassword, HttpServletRequest request);


    /**
     * 用户查询
     * @param userName 用户昵称
     * @return 用户
     */
    List<user> userSearch(String userName, HttpServletRequest request);

    /**
     * 用户删除
     * @param userId 用户id
     * @return 是否删除
     */
    boolean userDelete(Integer userId, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param u 原生用户
     * @return 干净用户
     */
    user getCleanUser(user u);

    /**
     * 用户注销
     * @param request
     */
    void userLogout(HttpServletRequest request);
}
