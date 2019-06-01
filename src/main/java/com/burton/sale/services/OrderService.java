package com.burton.sale.services;

import com.burton.sale.commons.PageRequest;
import com.burton.sale.dtos.OrderDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Burton
 * @title: OrderService
 * @projectName sale
 * @description:
 * @date 2019/5/29 10:51
 */
public interface OrderService {

    /**
     * 创建订单
     */
    OrderDTO creatOrder(OrderDTO orderDTO);

    /**
     * 查询订单
     */
    OrderDTO getOrderById(String orderId);

    /**
    查询订单列表
     */
    PageInfo<OrderDTO> listOrdersByBuyerOpenId(String buyerOppenid, PageRequest pageRequest);

    /**
     * 取消订单
     */
    OrderDTO cancleOrder(OrderDTO orderDTO);

    /**
     * 完结订单
     */
    OrderDTO finishOrder(OrderDTO orderDTO);

    /**
     * 支付订单
     */
    OrderDTO payOrder(OrderDTO orderDTO);
}
