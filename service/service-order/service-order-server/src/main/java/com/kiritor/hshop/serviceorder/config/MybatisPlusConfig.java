/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: MybatisPlusConfig
 */
package com.kiritor.hshop.serviceorder.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Class MybatisPlusConfig
 * @Description MybatisPlus配置信息
 * @Author 子梁
 * @Date 2020/7/9
 * @Verson 1.0.0
 *
 */

@EnableTransactionManagement
@Configuration
@MapperScan("com.kiritor.hshop.serviceorder.dao")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

