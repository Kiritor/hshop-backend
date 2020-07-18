package com.kiritor.hshop.serviceorder.service;

import com.kiritor.hshop.serviceorder.model.ReceiverAddress;

import java.util.List;

public interface ReceiverAddressService {
    ReceiverAddress getAddressById(Integer id);
    List<ReceiverAddress> getAddressListByUserId(Integer userId);
}
