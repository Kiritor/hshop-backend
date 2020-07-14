/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: MyAuthenticationExpireHandler
 */
package com.kiritor.hshop.serviceuser.security.handler;

import com.kiritor.hshop.common.entity.ResultBody;
import com.kiritor.hshop.common.enums.ResultCode;
import com.kiritor.hshop.common.utils.HttpResponseUitls;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Class MyAuthenticationExpireHandler
 * @Description 未登录或过期handler
 * @Author 子梁
 * @Date 2020/7/11
 * @Verson 1.0.0
 *
 */

public class LoginExpireHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        HttpResponseUitls.toJSONString(httpServletResponse, ResultBody.fail(ResultCode.USER_NOT_LOGIN));
    }
}
