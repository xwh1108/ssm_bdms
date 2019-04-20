package com.xie.service;

import com.xie.domain.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll() throws Exception;

    public void save(Product product) throws Exception;
}
