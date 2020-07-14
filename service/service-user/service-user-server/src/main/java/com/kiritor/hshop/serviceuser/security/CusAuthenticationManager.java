/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: CusAuthenticationManager
 */
package com.kiritor.hshop.serviceuser.security;

import com.kiritor.hshop.serviceuser.security.handler.CustomAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Class CusAuthenticationManager
 * @Description 自定义认证管理器
 * @Author 子梁
 * @Date 2020/7/9
 * @Verson 1.0.0
 *
 */

@Component
public class CusAuthenticationManager implements AuthenticationManager {

    private final CustomAuthenticationProvider customAuthenticationProvider;

    public CusAuthenticationManager(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //使用自定义认证过程取的认证结果
        Authentication result = customAuthenticationProvider.authenticate(authentication);
        if(Objects.nonNull(result)) {
            return result;
        }
        throw new ProviderNotFoundException("认证过程失败");
    }
}
