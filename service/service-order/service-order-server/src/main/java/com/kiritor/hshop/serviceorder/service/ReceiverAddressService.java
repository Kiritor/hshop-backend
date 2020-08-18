package com.kiritor.hshop.serviceorder.service;

import com.kiritor.hshop.serviceorder.model.ReceiverAddress;

import java.util.List;

public interface ReceiverAddressService {
    ReceiverAddress getAddressById(Integer id);
    List<ReceiverAddress> getAddressListByUserId(Integer userId);
    Integer addReceiverAddress(ReceiverAddress address);
    Integer updateReceiverAddress(ReceiverAddress address);
    Integer setDefaultAddress(ReceiverAddress address);
    Integer deleteReceiverAddress(String id);

}
