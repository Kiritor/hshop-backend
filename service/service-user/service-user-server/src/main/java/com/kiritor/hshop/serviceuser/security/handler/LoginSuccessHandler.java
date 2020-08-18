/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: LoginSuccessHandler
 */
package com.kiritor.hshop.serviceuser.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.kiritor.hshop.common.entity.ResultBody;
import com.kiritor.hshop.common.enums.ResultCode;
import com.kiritor.hshop.common.utils.HttpResponseUitls;
import com.kiritor.hshop.common.utils.JwtUtils;
import com.kiritor.hshop.serviceuser.dao.UserDao;
import com.kiritor.hshop.serviceuser.model.User;
import com.kiritor.hshop.serviceuser.security.model.Jwt;
import com.kiritor.hshop.serviceuser.security.model.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Class LoginSuccessHandler
 * @Description 认证成功处理逻辑,返回json
 * @Author 子梁
 * @Date 2020/7/10
 * @Verson 1.0.0
 *
 */

@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserDao userDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        boolean login = true;
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();

        long now = System.currentTimeMillis();
        User user = securityUser.getCurrentUser();
        // 更新用户last_login_time
        user.setLastLoginTime(new Date());
        userDao.updateById(user);
        user.setImage("");

        //设置密码为空
        securityUser.setPassword(null);
        JSONObject payload = new JSONObject();
        // 签发人
        payload.put("iss","sys");
        // 受众
        payload.put("aud",securityUser.getUsername());
        // 过期时间
        payload.put("exp",now + JwtUtils.EXPIRE_TIME);
        // 生效时间
        payload.put("nbf",now);
        // 签发时间
        payload.put("iat",now);
        // 编号
        payload.put("jti", user.getId());
        // 主题
        payload.put("sub","JWT-TEST");
        // 用户对象
        payload.put("user",securityUser);

        try {
            response.setHeader(JwtUtils.HEADER_TOKEN_NAME, new Jwt(payload.toJSONString()).toString());
        } catch (Exception e) {
            login = false;
        }
        if (login){
            HttpResponseUitls.toJSONString(response,ResultBody.ok(ResultCode.USER_LOGIN_SUCCESS,user));
        }else{
            HttpResponseUitls.toJSONString(response,ResultBody.ok(ResultCode.USER_LOGIN_FAIL));
        }
    }
}