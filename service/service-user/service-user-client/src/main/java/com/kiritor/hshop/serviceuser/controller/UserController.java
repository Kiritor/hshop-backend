/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: UserController
 */
package com.kiritor.hshop.serviceuser.controller;

import com.kiritor.hshop.serviceuser.client.UserClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Class UserController
 * @Description
 * @Author 子梁
 * @Date 2020/7/6
 * @Verson 1.0.0
 *
 */

@RestController
@RequestMapping("/")
public class UserController {

    @Resource
    UserClient userClient;
    @GetMapping(value = "hi")
    public ResponseEntity hi() {
        return  ResponseEntity.ok(userClient.getUserList());
    }
}
