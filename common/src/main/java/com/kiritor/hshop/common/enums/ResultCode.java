/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: ResultCode
 */
package com.kiritor.hshop.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @Class ResultCode
 * @Description
 *   异常唯一标识,代码及异常信息
 * @Author 子梁
 * @Date 2020/7/7
 * @Verson 1.0.0
 *
 */

@AllArgsConstructor
@Getter
public enum ResultCode {
    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(HttpStatus.OK,1001, "参数无效"),
    PARAM_IS_BLANK(HttpStatus.OK,1002, "参数为空"),
    PARAM_TYPE_ERROR(HttpStatus.OK,1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(HttpStatus.OK,1004, "参数缺失"),

    /* 用户错误 */
    USER_LOGIN_SUCCESS(HttpStatus.OK,2000, "登录成功!"),
    USER_LOGIN_FAIL(HttpStatus.OK,2012,"登录失败!"),
    USER_LOGOUT_SUCCESS(HttpStatus.OK,2011,"退出登录成功!"),
    USER_NOT_LOGIN(HttpStatus.OK,2001, "用户未登录"),
    USER_NOT_FOUND(HttpStatus.OK,2010,"用户不存在"),
    AUTH_TOKEN_ERROR(HttpStatus.METHOD_NOT_ALLOWED,2011,"TOKEN校验失败!"),
    USER_ACCOUNT_EXPIRED(HttpStatus.OK,2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(HttpStatus.OK,2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(HttpStatus.OK,2004, "密码过期"),
    USER_ACCOUNT_DISABLE(HttpStatus.OK,2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(HttpStatus.OK,2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(HttpStatus.OK,2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(HttpStatus.OK,2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(HttpStatus.OK,2009, "账号下线"),

    /* 业务错误 */
    NO_PERMISSION(HttpStatus.OK,3001, "没有权限"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,500,"未知错误,服务器内部异常"),
    SUCCESS(HttpStatus.OK,200,"SUCCESS"),
    CONTENT_TYPE_NO_SUPORT(HttpStatus.BAD_REQUEST,400,"不支持的请求头类型");

    private HttpStatus status;
    private int code;
    private String message;
}
