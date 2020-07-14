/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: FeignConfig
 */
package com.kiritor.hshop.serviceuser.client.config;

import feign.Logger;
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
public class FeignConfig {

    //日志配置

    @Bean
    Logger.Level feignLoggLevel(){
        return Logger.Level.FULL;
    }

}
