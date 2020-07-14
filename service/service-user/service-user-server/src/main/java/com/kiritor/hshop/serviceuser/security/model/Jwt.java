/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: Jwt
 */
package com.kiritor.hshop.serviceuser.security.model;

import com.kiritor.hshop.serviceuser.security.utils.JwtUtils;
import lombok.Data;

/**
 * @Class Jwt
 * @Description
 * @Author 子梁
 * @Date 2020/7/12
 * @Verson 1.0.0
 *
 */

@Data
public class Jwt {

    private String header;
    private String payload;
    private String signature;

    public Jwt(String payload) throws Exception{
        this.header = JwtUtils.encode(JwtUtils.DEFAULT_HEADER);
        this.payload = JwtUtils.encode(payload);
        this.signature = JwtUtils.getSignature(payload);
    }

    /**
     * JWT
     * @return
     */
    @Override
    public String toString() {
        return header+"." + payload + "." + signature;
    }
}
