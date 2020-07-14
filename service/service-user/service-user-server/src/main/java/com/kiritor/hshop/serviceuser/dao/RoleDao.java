package com.kiritor.hshop.serviceuser.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kiritor.hshop.serviceuser.model.Role;

import java.util.List;

public interface RoleDao extends BaseMapper {
    List<Role> getRoleListByUrl(String url);
    List<Role> getRoleListByUserName(String username);
 }
