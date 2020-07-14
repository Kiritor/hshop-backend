package com.kiritor.hshop.serviceuser.service;

import com.kiritor.hshop.serviceuser.model.Permission;

import java.util.List;

public interface PermissionService {
    /**
     * 根据用户id得到权限规则
     * @param userId
     * @return
     */
    List<Permission> getPermissionListByUserId(String userId);

    /**
     * 根据路径得到权限规则
     * @param path
     * @return
     */
    List<Permission> getPermissionListByPath(String path);
}
