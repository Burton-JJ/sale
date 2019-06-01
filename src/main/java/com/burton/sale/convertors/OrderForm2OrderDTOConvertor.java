package com.burton.sale.convertors;

import com.burton.sale.beans.OrderDetail;
import com.burton.sale.dtos.OrderDTO;
import com.burton.sale.enums.ResultEnum;
import com.burton.sale.exceptions.SaleException;
import com.burton.sale.forms.OrderForm;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.ORB;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Burton
 * @location：com.burton.sale.convertors.OrderForm2OrderDTOConvertor
 * @title: OrderForm2OrderDTOConvertor
 * @projectName sale
 * @description:
 * @date 2019/6/1 23:33
 */
@Slf4j
public class OrderForm2OrderDTOConvertor {

    public static OrderDTO formToDTO(OrderForm form) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(form.getBuyerName());
        orderDTO.setBuyerAddress(form.getBuyerAddress());
        orderDTO.setBuyerOpenid(form.getBuyerOpenId());
        orderDTO.setBuyerPhone(form.getBuyerPhone());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(form.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (JsonSyntaxException e) {
            log.error("[类型转换]错误，json={}", form.getItems());
            throw new SaleException(ResultEnum.JSON_TO_OBJECT_FAIL);
        }
        orderDTO.setOrderDetails(orderDetailList);
        return orderDTO;
    }
}
