package com.zgm.cloud.service;

import com.zgm.cloud.dao.UserMapper;
import com.zgm.cloud.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 15/03/2020 17:45
 * @Website https://www.zhangguimin.cn
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //获取本地用户
        User user = userMapper.selectByUserName(userName);
        if (user != null) {
            //返回oauth2的用户
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassWord(),
                    AuthorityUtils.createAuthorityList(user.getRole()));
        } else {
            throw new UsernameNotFoundException("用户[" + userName + "]不存在");
        }
    }
}