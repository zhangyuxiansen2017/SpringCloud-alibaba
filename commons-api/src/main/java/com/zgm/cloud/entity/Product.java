package com.zgm.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * product
 * @author 
 */
@Data
public class Product implements Serializable {
    private Long id;

    private String name;

    private Integer num;

    private Date createDate;

    private static final long serialVersionUID = 1L;
}