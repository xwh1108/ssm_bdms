package com.xie.controller;


import com.xie.domain.Product;
import com.xie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProudctController {

    @Autowired
    ProductService productService;


    @RequestMapping("findAll")
    public ModelAndView findAll() throws Exception {
        List<Product> productList = productService.findAll();
        ModelAndView mav=new ModelAndView();
        mav.addObject("productList",productList);
        mav.setViewName("product-list");
        return mav;
    }

    @RequestMapping("save")
    public String save(Product product) throws Exception{
        productService.save(product);
        return "redirect:findAll";
    }
}
