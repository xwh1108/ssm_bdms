package com.xie.service.Impl;

import com.github.pagehelper.PageHelper;
import com.xie.dao.OrdersDao;
import com.xie.domain.Orders;
import com.xie.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersDao ordersDao;

    @Override
    public List<Orders> findAllByPage(int page, int pageSize) throws Exception {
        PageHelper.startPage(page, pageSize);
        return ordersDao.findAllByPage();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return ordersDao.findById(id);
    }
}
