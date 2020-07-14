/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: Permissin
 */
package com.kiritor.hshop.serviceuser.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Class Permissin
 * @Description
 * @Author 子梁
 * @Date 2020/7/7
 * @Verson 1.0.0
 *
 */

@Data
public class Permission implements Serializable {

    private Integer id;
    private String permissionCode;
    private String permissionName;

}
