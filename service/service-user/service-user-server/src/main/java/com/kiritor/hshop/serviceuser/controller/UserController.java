package com.kiritor.hshop.serviceuser.controller;

import com.kiritor.hshop.common.controller.BaseController;
import com.kiritor.hshop.common.entity.ResultBody;
import com.kiritor.hshop.common.enums.ResultCode;
import com.kiritor.hshop.serviceuser.model.User;
import com.kiritor.hshop.serviceuser.security.utils.PassToken;
import com.kiritor.hshop.serviceuser.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserController extends BaseController {
    @Resource
    UserService userService;

    @GetMapping("/")
    @PreAuthorize("hasRole('system')")
    @ResponseBody
    public ResultBody getUser() {
        List<User> users = userService.getUserList();
        return ResultBody.ok(ResultCode.SUCCESS,users);
    }

    @GetMapping("/hello")
    @PassToken(required = true)
    public String hello(){
        return "SUCCESS";
    }

    @GetMapping("/hello1")
    @PreAuthorize("hasRole('HELLO')")
    public String hello1(){
        return "SUCCESS";
    }

}
