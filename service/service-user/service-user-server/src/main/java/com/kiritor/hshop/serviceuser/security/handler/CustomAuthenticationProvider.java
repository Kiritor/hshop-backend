/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: CustomAuthenticationProvider
 */
package com.kiritor.hshop.serviceuser.security.handler;

import com.kiritor.hshop.serviceuser.dao.RoleDao;
import com.kiritor.hshop.serviceuser.dao.UserDao;
import com.kiritor.hshop.serviceuser.model.Role;
import com.kiritor.hshop.serviceuser.model.User;
import com.kiritor.hshop.serviceuser.security.model.SecurityUser;
import com.kiritor.hshop.serviceuser.security.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @Class CustomAuthenticationProvider
 * @Description 自定义认证处理
 * @Author 子梁
 * @Date 2020/7/9
 * @Verson 1.0.0
 *
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取前端输入的用户名、密码
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        User userInfo = userDao.getUserByName(username);
        if(userInfo==null){
            throw new UsernameNotFoundException("用户未找到");
        }
        //密码认证是否通过
        boolean isValid = PasswordUtils.isValidPassword(password,userInfo.getPassword(),userInfo.getSalt());
        if(!isValid){
            throw new BadCredentialsException("密码错误!");
        }

        // 前后端分离情况下的处理逻辑
        // 更新登录令牌-之后访问系统其他接口直接通过token认证用户授权
        String token = PasswordUtils.encodePassword(System.currentTimeMillis() + userInfo.getSalt(), userInfo.getSalt());
        userInfo.setToken(token);
        userDao.updateById(userInfo);
        List<Role> roles = roleDao.getRoleListByUserName(username);
        SecurityUser securityUser = new SecurityUser(userInfo,roles);
        return new UsernamePasswordAuthenticationToken(securityUser, password, securityUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
