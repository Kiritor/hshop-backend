package com.kiritor.hshop.serviceuser.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kiritor.hshop.serviceuser.model.Permission;

import java.util.List;

public interface PermissionDao extends BaseMapper<Permission> {
    /**
     * 根据用户id得到权限规则
     * @param userId
     * @return
     */
    List<Permission> getPermissionListByUserId(String userId);

    /**
     * 根据请求路径得到权限规则
     * @param path
     * @return
     */
    List<Permission> getPermissionListByPath(String path);
}
