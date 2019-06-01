package com.burton.sale.services.impl;

import com.burton.sale.beans.OrderDetail;
import com.burton.sale.commons.PageRequest;
import com.burton.sale.dtos.CartDTO;
import com.burton.sale.dtos.OrderDTO;
import com.burton.sale.enums.OrderStatusEnum;
import com.burton.sale.enums.PayStatusEnum;
import com.burton.sale.enums.ResultEnum;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Action;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    // TODO: 2019/6/1 参数化测试

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void creatOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("江四");
        orderDTO.setBuyerAddress("留和路3588号");
        orderDTO.setBuyerOpenid("3310671997xxid");
        orderDTO.setBuyerPhone("13568145634");
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1150299344");
        orderDetail.setProductQuantity(3);
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("1150299345");
        orderDetail2.setProductQuantity(3);
        OrderDetail orderDetail3 = new OrderDetail();
        orderDetail3.setProductId("1150299346");
        orderDetail3.setProductQuantity(3);
        orderDetails.add(orderDetail);
        orderDetails.add(orderDetail2);
        orderDetails.add(orderDetail3);
        orderDTO.setOrderDetails(orderDetails);
        orderService.creatOrder(orderDTO);
    }

    @Test
    public void getOrderById() {
        OrderDTO orderDTO = orderService.getOrderById("1559139623514872027");
    }

    @Test
    public void listOrdersByBuyerOpenId() {
        PageInfo<OrderDTO> pageInfo = orderService.listOrdersByBuyerOpenId("3308221996xxid", new PageRequest(2, 1));
        Assert.assertEquals(2, pageInfo.getSize());
    }

    @Test
    public void cancleOrder() {
        OrderDTO orderDTO = orderService.getOrderById("1559135931394919406");
        OrderDTO resultOrderDTO = orderService.cancleOrder(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCLE.getOrderStatusCode(), resultOrderDTO.getOrderStatus());
    }

    @Test
    public void finishOrder() {
        OrderDTO orderDTO = orderService.getOrderById("1559136833952893161");
        OrderDTO resultOrderDTO = orderService.finishOrder(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getOrderStatusCode(), resultOrderDTO.getOrderStatus());
    }

    // TODO: 2019/6/1 参数化测试
    @Test
    public void payOrder() {
        OrderDTO orderDTO = orderService.getOrderById("1559139623514872027");
        OrderDTO resultOrderDTO = orderService.payOrder(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getPayStatusCode(), resultOrderDTO.getPayStatus());
    }
}