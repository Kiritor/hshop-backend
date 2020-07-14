/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: UrlRolesFilterHandler
 */
package com.kiritor.hshop.serviceuser.security.handler;

import com.kiritor.hshop.serviceuser.dao.RoleDao;
import com.kiritor.hshop.serviceuser.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Class UrlRolesFilterHandler
 * @Description
 * @Author 子梁
 * @Date 2020/7/11
 * @Verson 1.0.0
 *
 */

@Service
public class UrlRolesFilterHandler implements FilterInvocationSecurityMetadataSource {

    @Autowired(required = false)
    private RoleDao roleDao;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Role> roleList = roleDao.getRoleListByUrl(requestUrl);
        List<String> roleNames = new ArrayList<>();
        for(Role role:roleList){
            roleNames.add(role.getRoleCode());
        }
        String[] names = new String[roleNames.size()];
        for (int i = 0; i < roleNames.size(); i++) {
            names[i] = roleNames.get(i);
        }
        return SecurityConfig.createList(names);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}