package com.burton.sale.services.impl;

import com.burton.sale.beans.ProductInfo;
import com.burton.sale.commons.PageRequest;
import com.burton.sale.dtos.CartDTO;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void getProduct() {
    }

    @Test
    public void listUProducts() {
    }

    @Test
    public void listAllProducts() {
        PageInfo<ProductInfo> pageInfo = productService.listAllProducts(new PageRequest(1,3));
        List<ProductInfo> list = pageInfo.getList();
        System.out.println(list.size());
        for (ProductInfo productInfo : list) {
            System.out.println(productInfo);
        }
    }

    @Test
    public void savaProduct() {
        ProductInfo productInfo = new ProductInfo("1150299001", "波司登X1羽绒服",
                new BigDecimal("1099"), 200, "保暖防风", "icon", 2, null, null, 0);
        ProductInfo productInfo1 = productService.savaProduct(productInfo);
        Assert.assertNotNull(productInfo1);
    }

    @Test
    public void decreaseStock() {
        List<CartDTO> cartDTOS = new ArrayList<>();
        CartDTO cartDTO = new CartDTO(2, "1150299347");
        cartDTOS.add(cartDTO);
        productService.decreaseStock(cartDTOS);

    }
}