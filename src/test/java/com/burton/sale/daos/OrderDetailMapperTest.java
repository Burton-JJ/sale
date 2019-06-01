package com.burton.sale.daos;

import com.burton.sale.beans.OrderMaster;
import com.burton.sale.daos.examples.OrderMasterExample;
import com.github.pagehelper.PageHelper;
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
public class OrderDetailMapperTest {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Test
    public void savaOrderMaster() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("201905282131004");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerAddress("留下16号");
        orderMaster.setBuyerOpenid("3302671989xxid");
        orderMaster.setBuyerPhone("13356789054");
        orderMaster.setOrderAmount(new BigDecimal("799"));
        int result = orderMasterMapper.insertSelective(orderMaster);
        Assert.assertEquals(1, result);
    }

    @Test
    public void getOrderByBuyerOpenid() {
        OrderMasterExample example = new OrderMasterExample();
        OrderMasterExample.Criteria criteria = example.createCriteria();
        criteria.andBuyerOpenidEqualTo("3308221996xxid");
        PageHelper.startPage(1,2);
        List<OrderMaster> orderMasters = orderMasterMapper.selectByExample(example);
        Assert.assertEquals(2, orderMasters.size());
    }


}