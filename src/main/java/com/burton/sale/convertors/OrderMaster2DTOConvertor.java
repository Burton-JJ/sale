package com.burton.sale.convertors;

import com.burton.sale.beans.OrderDetail;
import com.burton.sale.beans.OrderMaster;
import com.burton.sale.dtos.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Burton
 * @location：com.burton.sale.convertors.OrderMaster2DTOConvertor
 * @title: OrderMaster2DTOConvertor
 * @projectName sale
 * @description: OrderMaster --> OrderDTO
 * @date 2019/5/30 14:14
 */
public class OrderMaster2DTOConvertor {

    /**
     * 单个orderMaster --》orderDTO 无orderDetail
     * @param orderMaster
     * @return
     */
    public static OrderDTO masterToDTO(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    /**
     * 单个orderMaster --》orderDTO + orderDetail列表
     * @param orderMaster
     * @param orderDetailList
     * @return
     */
    public static OrderDTO masterToDTO(OrderMaster orderMaster, List<OrderDetail> orderDetailList) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetails(orderDetailList);
        return orderDTO;
    }

    /**
     * orderMasters列表 --》OrderDTO列表 无orderDetail
     * @param orderMasters
     * @return
     */
    public static List<OrderDTO> masterToDTOList(List<OrderMaster> orderMasters) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (OrderMaster orderMaster : orderMasters) {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

}
