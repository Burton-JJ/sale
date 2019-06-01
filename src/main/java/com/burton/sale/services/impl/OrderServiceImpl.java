package com.burton.sale.services.impl;

import com.burton.sale.beans.OrderDetail;
import com.burton.sale.beans.OrderMaster;
import com.burton.sale.beans.ProductInfo;
import com.burton.sale.commons.PageRequest;
import com.burton.sale.commons.StockBusiness;
import com.burton.sale.convertors.OrderMaster2DTOConvertor;
import com.burton.sale.daos.OrderDetailMapper;
import com.burton.sale.daos.OrderMasterMapper;
import com.burton.sale.daos.ProductInfoMapper;
import com.burton.sale.daos.examples.OrderDetailExample;
import com.burton.sale.daos.examples.OrderMasterExample;
import com.burton.sale.dtos.CartDTO;
import com.burton.sale.dtos.OrderDTO;
import com.burton.sale.enums.OrderStatusEnum;
import com.burton.sale.enums.PayStatusEnum;
import com.burton.sale.enums.ResultEnum;
import com.burton.sale.exceptions.SaleException;
import com.burton.sale.services.OrderService;
import com.burton.sale.utils.KeyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Burton
 * @location：com.burton.sale.services.impl.OrderServiceImpl
 * @title: OrderServiceImpl
 * @projectName sale
 * @description:
 * @date 2019/5/29 11:14
 */
@Service
//@Slf4j
public class OrderServiceImpl implements OrderService {
    // TODO: 2019/6/1  日志记录抛错堆栈信息
    //用以下方式 或者lombok的注解 //@Slf4j
    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private StockBusiness stockBusiness;
    
    /**
     * 创建订单
     * @param orderDTO
     */
    @Override
    @Transactional //事务注解
    public OrderDTO creatOrder(OrderDTO orderDTO) {
        //购物车(增减库存入参)
        List<CartDTO> cartDTOS = new ArrayList<>();
        //创建订单id
        String orderId = KeyUtil.generateUniqueKey();
        //商品总价
        // TODO: 2019/5/29 为什么不是 new BigDecimal(0);
        BigDecimal orderAmount = new BigDecimal("0");
        //region 查询商品(数量 价格等) 计算总价 写入订单详情表order_detail
        for (OrderDetail orderDetail : orderDTO.getOrderDetails()) {
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SaleException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //将每一个订单中的商品及对于的数量记录下来 放入购物车
            CartDTO cartDTO = new CartDTO(orderDetail.getProductQuantity(), orderDetail.getProductId());
            cartDTOS.add(cartDTO);
            //2.计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //3.order_detail写入数据库
            orderDetail.setOrderId(orderId);
            //创建订单明细id
            orderDetail.setDetailId(KeyUtil.generateUniqueKey());
            orderDetail.setProductName(productInfo.getProductName());
            orderDetail.setProductIcon(productInfo.getProductIcon());
            orderDetail.setProductPrice(productInfo.getProductPrice());
            //BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailMapper.insertSelective(orderDetail);
        }
        //endregion
        //region 写入数据库order_master
        OrderMaster orderMaster = new OrderMaster();
        //必须放在下面两句前面 不然会覆盖
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        //显示赋值，即使不赋值 数据库会有默认值
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getPayStatusCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getOrderStatusCode());
        orderMasterMapper.insertSelective(orderMaster);
        //endregion
        //region 创建订单后扣库存
        stockBusiness.decreaseStock(cartDTOS);
        //endregion
        orderDTO.setOrderId(orderId);
        return orderDTO;
    }

    /**
     * 查询订单
     * @param orderId
     */
    @Override
    public OrderDTO getOrderById(String orderId) {
        //region 判断订单是否存在
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId);
        if (orderMaster == null) {
            throw new SaleException(ResultEnum.ORDER_NOT_EXIST);
        }
        //endregion
        //region 查询订单详情
        OrderDetailExample orderDetailExample = new OrderDetailExample();
        OrderDetailExample.Criteria criteria = orderDetailExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(orderDetailExample);
        if (orderDetails == null) {
            throw new SaleException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        //endregion
        //region 组装并返回订单所有信息
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetails(orderDetails);
        return orderDTO;
        //endregion
    }

    /**
     * 查询订单列表
     *
     * @param buyerOppenid
     * @param pageRequest
     */
    @Override
    public PageInfo<OrderDTO> listOrdersByBuyerOpenId(String buyerOppenid, PageRequest pageRequest) {
        //region 分页查询订单列表
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        OrderMasterExample orderMasterExample = new OrderMasterExample();
        OrderMasterExample.Criteria criteria = orderMasterExample.createCriteria();
        criteria.andBuyerOpenidEqualTo(buyerOppenid);
        List<OrderMaster> orderMasters = orderMasterMapper.selectByExample(orderMasterExample);
        //endregion
        //region 结果转换并返回
        List<OrderDTO> orderDTOS = OrderMaster2DTOConvertor.masterToDTOList(orderMasters);
        PageInfo<OrderDTO> pageInfo = new PageInfo<>(orderDTOS);
        return pageInfo;
        //endregion
    }

    /**
     * 取消订单
     *
     * @param orderDTO
     */
    @Override
    @Transactional
    public OrderDTO cancleOrder(OrderDTO orderDTO) {
        //region 判断订单状态
        if (!OrderStatusEnum.NEW.getOrderStatusCode().equals(orderDTO.getOrderStatus())) {
            logger.error("【取消订单】订单状态不正确，只有新订单才可以被取消,orderStatus={},orderId={}",
                    orderDTO.getOrderStatus(), orderDTO.getOrderId());
            throw new SaleException(ResultEnum.NEW_ORDER_CANCLE);
        }
        //endregion
        //region 修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.CANCLE.getOrderStatusCode());
        orderMaster.setOrderStatus(OrderStatusEnum.CANCLE.getOrderStatusCode());
        orderMaster.setOrderId(orderDTO.getOrderId());
//        BeanUtils.copyProperties(orderDTO, orderMaster);
        int result = orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
        //更新数据库失败
        if (result != 1) {
            logger.error("订单取消数据库更新失败，orderStatus={},orderId={}",
                   orderDTO.getOrderStatus(), orderDTO.getOrderId());
            throw new SaleException(ResultEnum.ORDER_CANCLE_FAIL);
        }
        //endregion
        //region 订单取消 库存增加
        //返还库存 返还前先判断订单中是否有商品
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())) {
            logger.info("订单中不存在商品，订单号orderId={}",orderDTO.getOrderId());
            throw new SaleException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        //创建增加库存入参
        List<CartDTO> cartDTOS = new ArrayList<>();
        for (OrderDetail orderDetail : orderDTO.getOrderDetails()) {
            CartDTO cartDTO = new CartDTO(orderDetail.getProductQuantity(), orderDetail.getProductId());
            cartDTOS.add(cartDTO);
        }
        //增加库存
        stockBusiness.increaseStock(cartDTOS);
        //endregion
        //region 若已支付 需要退款
        if (PayStatusEnum.SUCCESS.getPayStatusCode().equals(orderDTO.getPayStatus())) {
            // TODO: 2019/5/31 退款
        }
        //endregion
        return orderDTO;
    }

    /**
     * 完结订单
     *
     * @param orderDTO
     */
    @Override
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        //region 判断订单状态
        if (!OrderStatusEnum.NEW.getOrderStatusCode().equals(orderDTO.getOrderStatus())) {
            logger.error("【完成订单】订单状态不正确，只有新订单才可以被完结,orderStatus={},orderId={}",
                    orderDTO.getOrderStatus(), orderDTO.getOrderId());

            throw new SaleException(ResultEnum.NEW_ORDER_FINISH);
        }
        //endregion
        //region 修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getOrderStatusCode());
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getOrderStatusCode());
        orderMaster.setOrderId(orderDTO.getOrderId());
//        BeanUtils.copyProperties(orderDTO, orderMaster);
        int result = orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
        //更新数据库失败
        if (result != 1) {
            logger.error("订单完结数据库更新失败，orderStatus={},orderId={}",
                    orderDTO.getOrderStatus(), orderDTO.getOrderId());
            throw new SaleException(ResultEnum.ORDER_FINISH_FAIL);
        }
        //endregion
        return orderDTO;
    }

    /**
     * 支付订单
     * @param orderDTO
     */
    @Override
    public OrderDTO payOrder(OrderDTO orderDTO) {
        //region 判断订单状态和支付状态
        //判断订单状态
        if (!OrderStatusEnum.NEW.getOrderStatusCode().equals(orderDTO.getOrderStatus())) {
            logger.error("【支付订单】订单状态不正确，只有新订单才可以被支付,orderStatus={},orderId={}",
                    orderDTO.getOrderStatus(), orderDTO.getOrderId());
            throw new SaleException(ResultEnum.NEW_ORDER_PAY);
        }
        //判断支付状态
        if (!PayStatusEnum.WAIT.getPayStatusCode().equals(orderDTO.getPayStatus())) {
            logger.error("【支付订单】订单支付状态不正确，只有待支付订单才可以被支付,orderStatus={},orderId={}",
                    orderDTO.getOrderStatus(), orderDTO.getOrderId());
            throw new SaleException(ResultEnum.WAIT_PAY_ORDER_PAY);
        }
        //endregion
        // region 修改订单支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getPayStatusCode());
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getPayStatusCode());
        orderMaster.setOrderId(orderDTO.getOrderId());
//        BeanUtils.copyProperties(orderDTO, orderMaster);
        int result = orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
        //更新数据库失败
        if (result != 1) {
            logger.error("订单支付数据库更新失败，orderStatus={},orderId={}",
                    orderDTO.getOrderStatus(), orderDTO.getOrderId());
            throw new SaleException(ResultEnum.ORDER_PAY_FAIL);
        }
        //endregion
        return orderDTO;
    }
}
