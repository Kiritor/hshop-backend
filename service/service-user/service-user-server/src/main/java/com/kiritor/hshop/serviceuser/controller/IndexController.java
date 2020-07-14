/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: IndexController
 */
package com.kiritor.hshop.serviceuser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Class IndexController
 * @Description
 * @Author 子梁
 * @Date 2020/7/10
 * @Verson 1.0.0
 *
 */

@RestController
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
