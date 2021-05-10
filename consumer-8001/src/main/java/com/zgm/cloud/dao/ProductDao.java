package com.zgm.cloud.dao;


import com.zgm.cloud.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductDao {

    void subtractProduct(@Param("num")Integer num,@Param("productId") Long productId);

    Product getProduct(@Param("productId")Long productId);
}