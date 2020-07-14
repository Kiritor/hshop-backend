/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: Role
 */
package com.kiritor.hshop.serviceuser.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * @Class Role
 * @Description 角色
 * @Author 子梁
 * @Date 2020/7/11
 * @Verson 1.0.0
 *
 */

@Data
@TableName("sys_user")
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority,Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("role_name")
    private String roleName;
    @TableField("role_code")
    private String roleCode;
    @TableField("role_description")
    private String roleDescription;

    @Override
    public String getAuthority() {
        return this.roleCode;
    }
}
