package com.kiritor.hshop.serviceuser.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kiritor.hshop.serviceuser.model.User;

import java.util.List;

public interface UserDao  extends BaseMapper<User> {

    /**
     * 得到用户列表
     * @return
     */
    List<User> getUserList();

    /**
     * 根据用户名称得到用户
     * @param username
     * @return
     */
    User getUserByName(String username);

    /**
     * 根据用户名,一次性得到用户及权限信息
     * @param username
     * @return
     */
    User getRoleListByUserName(String username);
}
