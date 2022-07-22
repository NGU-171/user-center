/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.linbei.usercenter.common;

public enum ErrorCode {

    SUCESS(20000,"返回成功",""),
    PARAMA_ERROR(40000,"请求参数错误",""),
    DATA_NULL(400001,"返回结果空值",""),
    NOT_LOGIN(40100,"用户未登录",""),
    NO_AUTORITY(40101,"用户没有权限","")
    ;

    private int code;

    private String message;

    private String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }
}
