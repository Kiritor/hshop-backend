/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: ReceiverAddressDao
 */
package com.kiritor.hshop.serviceorder.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kiritor.hshop.serviceorder.model.ReceiverAddress;

import java.util.List;

/**
 * @Class ReceiverAddressDao
 * @Description
 * @Author 子梁
 * @Date 2020/7/16
 * @Verson 1.0.0
 *
 */

public interface ReceiverAddressDao extends BaseMapper<ReceiverAddress> {

    /**
     * 根据用户id得到收货地址列表
     * @param userId
     * @return
     */
    List<ReceiverAddress> getAddressListByUserId(Integer userId);


}
