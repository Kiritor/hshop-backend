/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: PassToken
 */
package com.kiritor.hshop.serviceuser.security.utils;

/**
 * @Class PassToken
 * @Description
 * @Author 子梁
 * @Date 2020/7/12
 * @Verson 1.0.0
 *
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 用来跳过验证的 PassToken
 * @author MRC
 * @date 2019年4月4日 下午7:01:25
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}