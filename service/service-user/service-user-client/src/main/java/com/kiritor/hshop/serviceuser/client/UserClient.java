/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: UserClient
 */
package com.kiritor.hshop.serviceuser.client;

import com.kiritor.hshop.serviceuser.client.config.FeignConfig;
import com.kiritor.hshop.serviceuser.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Class UserClient
 * @Description
 * @Author 子梁
 * @Date 2020/7/6
 * @Verson 1.0.0
 *
 */
@FeignClient(value = "service-user-server",configuration = FeignConfig.class)
@RequestMapping(value ="/v1/users")
interface UserClient {
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    User getUserList(@PathVariable(value="id") Integer id);
}
