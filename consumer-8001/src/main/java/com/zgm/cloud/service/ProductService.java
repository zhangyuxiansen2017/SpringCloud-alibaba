package com.zgm.cloud.service;

import com.zgm.cloud.entity.Product;

public interface ProductService {
    void subtractProduct(Integer num,Long productId);

    Product getProduct(Long productId);
}
