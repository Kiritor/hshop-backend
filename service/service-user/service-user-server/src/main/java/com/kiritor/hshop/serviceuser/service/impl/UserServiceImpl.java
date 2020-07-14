/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: UserServiceImpl
 */
package com.kiritor.hshop.serviceuser.service.impl;

import com.kiritor.hshop.serviceuser.dao.UserDao;
import com.kiritor.hshop.serviceuser.model.User;
import com.kiritor.hshop.serviceuser.service.PermissionService;
import com.kiritor.hshop.serviceuser.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Class UserServiceImpl
 * @Description
 * @Author 子梁
 * @Date 2020/7/8
 * @Verson 1.0.0
 *
 */

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private PermissionService permissionService;

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public int update(User user) {
        return userDao.updateById(user);
    }
}
