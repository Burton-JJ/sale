package com.burton.sale.dtos;

import com.burton.sale.beans.OrderDetail;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Burton
 * @location：com.burton.sale.dtos.OrderDTO
 * @title: OrderDTO
 * @projectName sale
 * @description: 订单主表加详情表
 * @date 2019/5/29 10:58
 */
@Getter
@Setter
public class OrderDTO {
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;
    /**
     * 订单详情
     */
    private List<OrderDetail> orderDetails;
}
