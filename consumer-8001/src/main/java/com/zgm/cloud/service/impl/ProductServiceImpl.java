package com.zgm.cloud.service.impl;

import com.zgm.cloud.dao.ProductDao;
import com.zgm.cloud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void subtractProduct(Long productId) {
        productDao.subtractProduct(productId);
    }
}
