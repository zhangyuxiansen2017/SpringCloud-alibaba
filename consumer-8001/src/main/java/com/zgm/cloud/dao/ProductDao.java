package com.zgm.cloud.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductDao {

    void subtractProduct(@Param("productId") Long productId);
}