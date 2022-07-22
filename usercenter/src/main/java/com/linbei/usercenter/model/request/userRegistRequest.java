package com.linbei.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class userRegistRequest implements Serializable {

    private static final long serialVersionUID = -5387576689563891890L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;


}
