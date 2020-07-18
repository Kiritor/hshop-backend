/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: ReceiverAddress
 */
package com.kiritor.hshop.serviceorder.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Class ReceiverAddress
 * @Description 收货地址
 * @Author 子梁
 * @Date 2020/7/16
 * @Verson 1.0.0
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("receiver_address")
public class ReceiverAddress implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    @TableField("receiver_name")
    private String receiverName;

    @TableField("receiver_phone")
    private String receiverPhone;

    @TableField("address")
    private String address;

    @TableField("isDefault")
    private Boolean isDefault;



}
