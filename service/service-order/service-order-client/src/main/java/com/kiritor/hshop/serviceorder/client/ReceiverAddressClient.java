/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: ReceiverAddressClient
 */
package com.kiritor.hshop.serviceorder.client;


import com.kiritor.hshop.common.entity.ResultBody;
import com.kiritor.hshop.serviceorder.client.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Class ReceiverAddressClient
 * @Description
 * @Author 子梁
 * @Date 2020/7/16
 * @Verson 1.0.0
 *
 */

@FeignClient(value = "service-order-server",configuration = FeignConfig.class)
@RequestMapping(value ="/v1/receiverAddress")
public interface ReceiverAddressClient {
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    ResultBody getAddressById(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET)
    ResultBody getAddressListByUserId(@PathVariable(value = "userId") Integer userId);

}
