package com.burton.sale.daos;

import com.burton.sale.beans.ProductInfo;
import com.burton.sale.daos.examples.ProductInfoExample;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoMapperTest {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Test
    public void queryProductInfo() {
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey("1150299106");
        System.out.println(productInfo);
    }

    @Test
    public void addProductInfoSelective() {
        ProductInfo productInfo = new ProductInfo("1150299347", "杜兰特篮球鞋",
                new BigDecimal("1099"), 200, "实战好鞋", "icon", 1, null, null, 0);
        productInfoMapper.insertSelective(productInfo);
    }

    @Test
    public void query() {
        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryTypeEqualTo(1).andProductIdEqualTo("1150299344");
        List<ProductInfo> productInfo = productInfoMapper.selectByExample(example);
        System.out.println(productInfo);

    }

    @Test
    public void upadte() {
        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryTypeEqualTo(1).andProductIdEqualTo("1150299344");
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("库里六代");
        productInfoMapper.updateByExampleSelective(productInfo, example);
    }

    @Test
    public void listProductByStatus() {
        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andProductStatusEqualTo(0);
        List<ProductInfo> productInfos = productInfoMapper.selectByExample(example);
        Assert.assertEquals(2, productInfos.size());
    }

}