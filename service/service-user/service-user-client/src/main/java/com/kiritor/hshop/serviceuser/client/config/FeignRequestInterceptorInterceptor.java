/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: FeignRequestInterceptorInterceptor
 */
package com.kiritor.hshop.serviceuser.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Class FeignRequestInterceptorInterceptor
 * @Description 自定义拦截器
 * @Author 子梁
 * @Date 2020/7/16
 * @Verson 1.0.0
 *
 */

public class FeignRequestInterceptorInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != attributes) {
            HttpServletRequest request = attributes.getRequest();
            if (null != request) {
                //添加token
                requestTemplate.header("authorization", request.getHeader("authorization"));
            }
        }
    }

}
