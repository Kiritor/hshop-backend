/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: MyAuthenticationFailureHandler
 */
package com.kiritor.hshop.serviceuser.security.handler;

import com.kiritor.hshop.common.entity.ResultBody;
import com.kiritor.hshop.common.enums.ResultCode;
import com.kiritor.hshop.common.utils.HttpResponseUitls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Class MyAuthenticationFailureHandler
 * @Description 认证失败处理handler,返回json(统一处理)
 * @Author 子梁
 * @Date 2020/7/10
 * @Verson 1.0.0
 *
 */

@Component
@Slf4j
@Deprecated
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    ResultBody result;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        log.debug(e.getMessage());
        if (e instanceof UsernameNotFoundException) {
            result = ResultBody.fail(ResultCode.USER_NOT_FOUND);
        } else if(e instanceof BadCredentialsException) {
            result = ResultBody.fail(ResultCode.USER_CREDENTIALS_ERROR);
        }
        else if (e instanceof LockedException) {
            result = ResultBody.fail(ResultCode.USER_ACCOUNT_LOCKED);
        } else if (e instanceof CredentialsExpiredException) {
            result = ResultBody.fail(ResultCode.USER_CREDENTIALS_EXPIRED);
        } else if (e instanceof AccountExpiredException) {
            result = ResultBody.fail(ResultCode.USER_ACCOUNT_EXPIRED);
        } else if (e instanceof DisabledException) {
            result = ResultBody.fail(ResultCode.USER_ACCOUNT_DISABLE);
        } else {
            result = ResultBody.fail(ResultCode.USER_LOGIN_FAIL);
        }
        HttpResponseUitls.toJSONString(httpServletResponse,result);
    }
}
