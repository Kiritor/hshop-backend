/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: User
 */
package com.kiritor.hshop.serviceuser.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Class User
 * @Description 用户表
 * @Author 子梁
 * @Date 2020/7/7
 * @Verson 1.0.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 915478504870211231L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    @TableField("account")
    private String account;
    @TableField("user_name")
    private String userName;
    @TableField("password")
    private String password;
    @TableField("last_login_time")
    private Date lastLoginTime;
    @TableField("image")
    private String image;
    @TableField("gender")
    private String gender;

    @TableField(value = "enabled", fill = FieldFill.INSERT)
    private Boolean enabled;

    @TableField(value = "account_non_expired",fill = FieldFill.INSERT)
    private Boolean accountNonExpired;

    @TableField(value = "account_non_locked",fill = FieldFill.INSERT)
    private Boolean accountNonLocked;

    @TableField(value = "credentials_non_expired",fill = FieldFill.INSERT)
    private Boolean credentialsNonExpired;
    @TableField("salt")
    private String salt;
    @TableField("token")
    private String token;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(value = "create_user",fill = FieldFill.INSERT)
    private Integer createUser;
    @TableField(value = "update_user",fill = FieldFill.INSERT_UPDATE)
    private Integer updateUser;

}
