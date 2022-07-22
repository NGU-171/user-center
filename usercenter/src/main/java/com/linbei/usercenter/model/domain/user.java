package com.linbei.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @TableName user
 */
@TableName(value ="user")
@Data
public class user implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String userImage;

    /**
     * 用户性别
     */
    private Integer userGender;

    /**
     * 用户电话
     */
    private String userPhone;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户邮箱
     */
    private String userMail;

    /**
     * 是否有效
     */
    private Integer isVaild;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 用户身份
     */
    private Integer userIdentity;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}