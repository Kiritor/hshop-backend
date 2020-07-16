package com.kiritor.hshop.serviceuser.controller;

import com.kiritor.hshop.common.controller.BaseController;
import com.kiritor.hshop.common.entity.ResultBody;
import com.kiritor.hshop.common.enums.ResultCode;
import com.kiritor.hshop.serviceuser.model.User;
import com.kiritor.hshop.serviceuser.security.utils.PassToken;
import com.kiritor.hshop.serviceuser.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Base64;

@RestController
@RequestMapping("v1/users")
public class UserController extends BaseController {
    @Resource
    UserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResultBody getUserInfo(@PathVariable(value = "id") Integer id) {
        User user = userService.getUserById(id);
        return ResultBody.ok(ResultCode.SUCCESS,user);
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


    /**
     * 根据用户id，修改用户avator
     * @param id
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping(value="/{id}")
    public ResultBody updateUserAvator(@PathVariable("id") Integer id,@RequestParam("avator") MultipartFile file){

        User user = userService.getUserById(id);
        try {
            byte[] data;
            data = file.getBytes();
            String imgStr = Base64.getEncoder().encodeToString(data);
            String contentType = file.getContentType();
            imgStr = "data:"+contentType+";base64," + imgStr;
            user.setImage(imgStr);
            //插入数据库
            userService.update(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultBody.ok(ResultCode.SUCCESS,user.getImage());
    }

    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResultBody updateUserGender(@RequestBody User user) {
        userService.update(user);
        return ResultBody.ok(ResultCode.SUCCESS);
    }


}
