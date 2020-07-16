/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: UserService
 */
package com.kiritor.hshop.serviceuser.service;

import com.kiritor.hshop.serviceuser.model.User;

import java.util.List;

/**
 * @Class UserService
 * @Description
 * @Author 子梁
 * @Date 2020/7/8
 * @Verson 1.0.0
 *
 */

public interface UserService {
    List<User> getUserList();
    User getUserByName(String username);
    int update(User user);
    User getUserById(int id);
}
