package com.zgm.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @Author Mr. Zhang
 * @Date 15/03/2020 17:39
 * @Website https://www.zhangguimin.cn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String userName;
    private String passWord;
    private String role;
}
