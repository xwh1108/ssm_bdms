package com.xie.service;

import com.xie.domain.Orders;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrdersService {
    List<Orders> findAllByPage(int page,int pagesize) throws Exception;

    Orders findById(String id) throws Exception;
}
