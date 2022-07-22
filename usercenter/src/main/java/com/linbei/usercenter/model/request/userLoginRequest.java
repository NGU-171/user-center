package com.linbei.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class userLoginRequest implements Serializable {

    private static final long serialVersionUID = -7146565361681480981L;

    private String userAccount;

    private String userPassword;


}
