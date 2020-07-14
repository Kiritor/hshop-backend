/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: Constants
 */
package com.kiritor.hshop.common.enums;

/**
 * @Class Constants
 * @Description 全局常用变量
 * @Author 子梁
 * @Date 2020/7/10
 * @Verson 1.0.0
 *
 */

public class Constants {
    /**
     * 密码加密相关
     */
    public static String SALT = "hshop";
    public static final int HASH_ITERATIONS = 1;

    /**
     * 请求头类型：
     * application/x-www-form-urlencoded ： form表单格式
     * application/json ： json格式
     */
    public static final String REQUEST_HEADERS_CONTENT_TYPE = "application/json";
}
