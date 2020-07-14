/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: ContentTypeNoSuportException
 */
package com.kiritor.hshop.serviceuser.exception;

import com.kiritor.hshop.common.enums.ResultCode;

import javax.naming.AuthenticationException;

/**
 * @Class ContentTypeNoSuportException
 * @Description 不支持的请求头异常
 * @Author 子梁
 * @Date 2020/7/10
 * @Verson 1.0.0
 *
 */

public class ContentTypeNoSuportException extends AuthenticationException {

    private final ResultCode resultCode;

    public ContentTypeNoSuportException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

}
