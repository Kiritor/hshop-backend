package com.kiritor.hshop.serviceorder;

import com.kiritor.hshop.common.web.security.WebSecurityConfigWithoutService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"com.kiritor.hshop.serviceorder"})
@EnableFeignClients
@EnableDiscoveryClient
@Import(value = { WebSecurityConfigWithoutService.class})
public class ServiceOrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderServerApplication.class, args);
    }

}
