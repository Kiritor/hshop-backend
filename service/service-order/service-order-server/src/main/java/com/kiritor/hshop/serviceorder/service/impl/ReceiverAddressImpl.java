/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: ReceiverAddressImpl
 */
package com.kiritor.hshop.serviceorder.service.impl;

import com.kiritor.hshop.serviceorder.dao.ReceiverAddressDao;
import com.kiritor.hshop.serviceorder.model.ReceiverAddress;
import com.kiritor.hshop.serviceorder.service.ReceiverAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Class ReceiverAddressImpl
 * @Description
 * @Author 子梁
 * @Date 2020/7/16
 * @Verson 1.0.0
 *
 */

@Service
public class ReceiverAddressImpl implements ReceiverAddressService {

    @Autowired
    private ReceiverAddressDao receiverAddressDao;
    @Override
    public ReceiverAddress getAddressById(Integer id) {
        return receiverAddressDao.selectById(id);
    }

    @Override
    public List<ReceiverAddress> getAddressListByUserId(Integer userId) {
        return receiverAddressDao.getAddressListByUserId(userId);
    }
}
