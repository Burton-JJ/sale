package com.burton.sale.forms;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Burton
 * @location：com.burton.sale.forms.OrderForm
 * @title: OrderForm
 * @projectName sale
 * @description:创建订单入参
 * @date 2019/6/1 14:04
 */
@Getter
@Setter
public class OrderForm {
     //买家姓名
     // TODO: 2019/6/1 改为oval校验
     @NotEmpty(message = "买家姓名不能为空")
     private String buyerName;

     //买家手机号码
     @NotEmpty(message = "买家电话不能为空")
     private String buyerPhone;

     //买家地址
     @NotEmpty(message = "买家地址不能为空")
     private String buyerAddress;

     //买家微信openid
     @NotEmpty(message = "买家微信openid不能为空")
     private String buyerOpenId;

     //购物车
     @NotEmpty(message = "购物车不能为空")
     private String items;
}
