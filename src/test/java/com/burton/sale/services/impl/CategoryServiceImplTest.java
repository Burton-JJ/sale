package com.burton.sale.services.impl;

import com.burton.sale.beans.ProductCategory;
import com.burton.sale.services.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void getCategory() {
        ProductCategory category = categoryService.getCategory(1);
        Assert.assertEquals(new Integer(1), category.getCategoryId());
    }

    @Test
    public void listAllCategory() {
        List<ProductCategory> productCategories = categoryService.listAllCategory();
        Assert.assertEquals(3, productCategories.size());
    }

    @Test
    public void listCatogoryByIds() {
        List<Integer> ids = Arrays.asList(1,2);
        List<ProductCategory> productCategories = categoryService.listCatogoryByIds(ids);
        Assert.assertEquals(2, productCategories.size());
    }

    @Test
    public void savaCategory() {
        ProductCategory productCategory = new ProductCategory("卫衣", 3);
        ProductCategory productCategoryResult = categoryService.savaCategory(productCategory);
        Assert.assertNotNull(productCategoryResult);
    }

    @Test
    public void getCategoryByType() {
        ProductCategory category = categoryService.getCategoryByType(1);
        Assert.assertEquals("鞋子", category.getCategoryName());
    }
}