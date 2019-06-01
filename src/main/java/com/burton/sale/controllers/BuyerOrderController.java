package com.burton.sale.controllers;

import com.burton.sale.convertors.OrderForm2OrderDTOConvertor;
import com.burton.sale.dtos.OrderDTO;
import com.burton.sale.enums.ResultEnum;
import com.burton.sale.exceptions.SaleException;
import com.burton.sale.forms.OrderForm;
import com.burton.sale.services.impl.OrderServiceImpl;
import com.burton.sale.utils.ExceptionUtil;
import com.burton.sale.utils.ResultVOUtil;
import com.burton.sale.vos.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.pattern.PathPattern;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Burton
 * @location：com.burton.sale.controllers.BuyerOrderController
 * @title: BuyerOrderController
 * @projectName sale
 * @description:
 * @date 2019/6/1 12:20
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
//    下面这个由上面 lombok的@Slf4j注解代替 直接log
//    private Logger logger = LoggerFactory.getLogger(BuyerOrderController.class);

    @Autowired
    private OrderServiceImpl orderService;

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> creatOrder(@Valid OrderForm orderForm,
                                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确，orderForm={}",orderForm);
            String paraErrorMsg = bindingResult.getFieldError().getDefaultMessage();
            System.out.println(paraErrorMsg);
            throw ExceptionUtil.createException(ResultEnum.PARAMS_ERROR.getCode(), paraErrorMsg);
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConvertor.formToDTO(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())) {
            log.error("[创建订单]购物车不能为空");
            throw new SaleException(ResultEnum.CART_EMPTY);
        }
        OrderDTO orderDTOResult = orderService.creatOrder(orderDTO);
        Map<String, String> returnMap = new HashMap<>(4);
        returnMap.put("orderId", orderDTOResult.getOrderId());
        return ResultVOUtil.success(returnMap);
    }

    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> listOrder(@RequestParam("openid") String openid,
                                              @RequestParam("pageNum") Integer pageNum,
                                              @RequestParam("pageSize") Integer pageSize) {
        if (StringUtils.isEmpty(openid)) {
            log.error("[订单列表查询]买家openid为空");

        }
        return null;

    }
}
