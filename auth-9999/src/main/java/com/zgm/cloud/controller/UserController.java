package com.zgm.cloud.controller;

import com.zgm.cloud.dao.UserMapper;
import com.zgm.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 15/03/2020 18:07
 * @Website https://www.zhangguimin.cn
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    public UserMapper userMapper;


    @GetMapping("/getByName")
    public User getByName(){
        return userMapper.selectByUserName("zhangjian");
    }

    /**
     * 获取授权的用户信息
     * @param principal 当前用户
     * @return 授权信息
     */
    @GetMapping("/current/get")
    public Principal user(Principal principal){
        return principal;
    }
}