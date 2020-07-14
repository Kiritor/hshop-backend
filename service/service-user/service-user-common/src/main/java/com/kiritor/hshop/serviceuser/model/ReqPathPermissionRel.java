/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: ReqPathPermissionRel
 */
package com.kiritor.hshop.serviceuser.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Class ReqPathPermissionRel
 * @Description
 * @Author 子梁
 * @Date 2020/7/7
 * @Verson 1.0.0
 *
 */

@Data
public class ReqPathPermissionRel implements Serializable {

    private Integer id;
    private Integer urlId;
    private Integer permissionId;

}
