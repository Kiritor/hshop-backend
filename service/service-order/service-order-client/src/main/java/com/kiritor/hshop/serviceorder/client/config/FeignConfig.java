/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: FeignConfig
 */
package com.kiritor.hshop.serviceorder.client.config;

import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Class FeignConfig
 * @Description
 * @Author 子梁
 * @Date 2020/7/6
 * @Verson 1.0.0
 *
 */

@Configuration
@Slf4j
public class FeignConfig {

    @Bean
    public Retryer feiRetryer() {
        return new Retryer.Default(100, 1000, 5);
    }

    @Bean
    public FeignRequestInterceptorInterceptor feignRequestInterceptorInterceptor() {
        return new FeignRequestInterceptorInterceptor();
    }

}
