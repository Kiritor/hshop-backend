/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: ReceiverAddressController
 */
package com.kiritor.hshop.serviceorder.controller;

import com.kiritor.hshop.common.entity.ResultBody;
import com.kiritor.hshop.common.enums.ResultCode;
import com.kiritor.hshop.serviceorder.model.ReceiverAddress;
import com.kiritor.hshop.serviceorder.service.ReceiverAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class ReceiverAddressController
 * @Description
 * @Author 子梁
 * @Date 2020/7/16
 * @Verson 1.0.0
 *
 */

@RestController
@RequestMapping("v1/receiverAddress")
public class ReceiverAddressController {

    @Autowired
    private ReceiverAddressService receiverAddressService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResultBody list(@PathVariable("id") Integer id) {
        return ResultBody.ok(ResultCode.SUCCESS,receiverAddressService.getAddressById(id));
    }

    @RequestMapping(value = "user/{userId}",method = RequestMethod.GET)
    public ResultBody getAddressLitByUserId(@PathVariable Integer userId){
        List<ReceiverAddress> addressList = receiverAddressService.getAddressListByUserId(userId);
        List<Map> addresses = new ArrayList<>();
        Map<String,Object> add = null;
        for(ReceiverAddress address:addressList ){
            add = new HashMap<>();
            add.put("id",address.getId());
            add.put("name",address.getReceiverName());
            add.put("tel",address.getReceiverPhone());
            add.put("address",address.getAddress());
            add.put("isDefault",address.getIsDefault());
            add.put("userId",address.getUserId());
            addresses.add(add);
        }
        return ResultBody.ok(ResultCode.SUCCESS,addresses);
    }
}
