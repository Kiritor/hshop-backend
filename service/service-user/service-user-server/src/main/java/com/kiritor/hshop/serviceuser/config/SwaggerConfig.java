/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: SwaggerConfig
 */
package com.kiritor.hshop.serviceuser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Class SwaggerConfig
 * @Description
 * @Author 子梁
 * @Date 2020/7/16
 * @Verson 1.0.0
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    /**
     * 设置一个开关，生产版本为false，关闭swagger
     */
    @Value("${swagger.enable}")
    private boolean enable;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).
                enable(enable).select().apis(RequestHandlerSelectors.basePackage("com.kiritor.hshop.serviceuser.controller")).
                //扫描包
                paths(PathSelectors.any()).build();
               //可以设置为扫描多个包

    }

    @SuppressWarnings("deprecation")
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("接口文档").
                description("service-user服务通用接口").version("1.0").build();
    }

    /**
     * 一定要写这个方法，不然访问swagger-ui.html页面会404
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").
                addResourceLocations("classpath:/META-INF/resources/").
                setCachePeriod(0);
    }
}
