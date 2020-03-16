package com.zgm.cloud.dao;

import com.zgm.cloud.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 15/03/2020 17:48
 * @Website https://www.zhangguimin.cn
 */
@Mapper
public interface UserMapper {
    User selectByUserName(String userName);
}
