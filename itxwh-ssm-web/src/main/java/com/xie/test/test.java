package com.xie.test;


import com.xie.dao.UserDao;
import com.xie.domain.Product;
import com.xie.domain.Role;
import com.xie.domain.UserInfo;
import com.xie.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:applicationContext.xml","classpath:spring-security.xml"})
public class test {

    @Autowired
    ProductService productService;

    @Autowired
    UserDao userDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void test1() throws Exception {
        List<Role> otherRole = userDao.findOtherRole("111-222");
        for (Role role : otherRole) {
            System.out.println("role.getRoleName() = " + role.getRoleName());
        }
    }



}
