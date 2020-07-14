/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: CustomAuthenticationProcessingFilter
 */
package com.kiritor.hshop.serviceuser.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.kiritor.hshop.common.utils.MultiReadHttpServletRequest;
import com.kiritor.hshop.serviceuser.model.User;
import com.kiritor.hshop.serviceuser.security.CusAuthenticationManager;
import com.kiritor.hshop.serviceuser.security.handler.LoginFailureHandler;
import com.kiritor.hshop.serviceuser.security.handler.LoginSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Class CustomAuthenticationProcessingFilter
 * @Description 自定义用户密码校验过滤器
 * @Author 子梁
 * @Date 2020/7/9
 * @Verson 1.0.0
 *
 */

@Slf4j
@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(CusAuthenticationManager cusAuthenticationManager, LoginSuccessHandler loginSuccessHandler,LoginFailureHandler loginFailureHandler) {
        super();
        this.setAuthenticationManager(cusAuthenticationManager);
        this.setAuthenticationFailureHandler(loginFailureHandler);
        this.setAuthenticationSuccessHandler(loginSuccessHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 前后端分离,请求头必须application/json格式
        if (request.getContentType() == null || !request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
              throw new AuthenticationServiceException("请求头类型不支持: " + request.getContentType());
        }

        UsernamePasswordAuthenticationToken authRequest;
        try {
            MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
            // 将前端传递的数据转换成jsonBean数据格式
            User user = JSONObject.parseObject(wrappedRequest.getBodyJsonStrByJson(wrappedRequest), User.class);
            authRequest = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword(), null);
            authRequest.setDetails(authenticationDetailsSource.buildDetails(wrappedRequest));
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }



    /*
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 前后端分离,请求头必须application/json格式
        if (request.getContentType() == null || !request.getContentType().contains(PasswordConstants.REQUEST_HEADERS_CONTENT_TYPE)) {
            throw new AuthenticationServiceException("请求头类型不支持: " + request.getContentType());
        }

        UsernamePasswordAuthenticationToken authRequest;
        try {
            MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
            // 将前端传递的数据转换成jsonBean数据格式
            User user = JSONObject.parseObject(wrappedRequest.getBodyJsonStrByJson(wrappedRequest), User.class);
            authRequest = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword(), null);
            authRequest.setDetails(authenticationDetailsSource.buildDetails(wrappedRequest));
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }
    */
}
