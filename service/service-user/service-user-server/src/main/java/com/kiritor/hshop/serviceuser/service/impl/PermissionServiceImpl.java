/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: PermissionServiceImpl
 */
package com.kiritor.hshop.serviceuser.service.impl;

import com.kiritor.hshop.serviceuser.dao.PermissionDao;
import com.kiritor.hshop.serviceuser.model.Permission;
import com.kiritor.hshop.serviceuser.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Class PermissionServiceImpl
 * @Description
 * @Author 子梁
 * @Date 2020/7/8
 * @Verson 1.0.0
 *
 */

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionDao permissionDao;

    @Override
    public List<Permission> getPermissionListByUserId(String userId) {
        return permissionDao.getPermissionListByUserId(userId);
    }

    @Override
    public List<Permission> getPermissionListByPath(String path) {
        return permissionDao.getPermissionListByPath(path);
    }
}
