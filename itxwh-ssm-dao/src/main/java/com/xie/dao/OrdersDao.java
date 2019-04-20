package com.xie.dao;

import com.xie.domain.Member;
import com.xie.domain.Orders;
import com.xie.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select =
                    "com.xie.dao.ProductDao.findById"))
    })
    List<Orders> findAllByPage();

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",javaType = Product.class,one = @One(select =
                    "com.xie.dao.ProductDao.findById")),
            @Result(column = "id",property = "travellers",javaType = java.util.List.class,many = @Many(select =
                    "com.xie.dao.TravellerDao.findByOrdersId")),
            @Result(column = "memberId",property = "member",javaType = Member.class,one = @One(select =
                    "com.xie.dao.MemberDao.findById")),
    })
    Orders findById(String id) throws Exception;
}
