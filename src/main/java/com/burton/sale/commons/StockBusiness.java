package com.burton.sale.commons;

import com.burton.sale.beans.ProductInfo;
import com.burton.sale.daos.ProductInfoMapper;
import com.burton.sale.dtos.CartDTO;
import com.burton.sale.enums.ResultEnum;
import com.burton.sale.exceptions.SaleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Burton
 * @location：com.burton.sale.commons.StockBusiness
 * @title: StockBusiness
 * @projectName sale
 * @description: 库存相关操纵 在多个service中都会用到 抽取出来
 * @date 2019/5/31 15:21
 */
@Component
public class StockBusiness {
    @Autowired
    private ProductInfoMapper productInfoMapper;

    /**
     * 扣减库存
     */
    public void decreaseStock(List<CartDTO> cartDTOS) {
        for (CartDTO cartDTO : cartDTOS) {
            //现根据id查出商品现有库存
            ProductInfo productInfoInDatebase = productInfoMapper.selectByPrimaryKey(cartDTO.getProductId());
            if (productInfoInDatebase == null) {
                throw new SaleException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //库存
            Integer oldStock = productInfoInDatebase.getProductStock();
            if (oldStock < 0) {
                throw new SaleException(ResultEnum.OLDSTOCK_NOT_ENOUGH);
            }
            //减后库存
            // TODO: 2019/5/29 此处减库存应该加锁
            Integer newStock = oldStock - cartDTO.getProducQquantity();
            if (newStock < 0) {
                throw new SaleException(ResultEnum.STOCK_NOT_ENOUGH);
            }
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductStock(newStock);
            productInfo.setProductId(cartDTO.getProductId());
            int result = productInfoMapper.updateByPrimaryKeySelective(productInfo);
            if (result <= 0) {
                throw new SaleException(ResultEnum.STOCK_DECREASE_FAIL);
            }
        }
    }

    /**
     * 增加库存
     */
    public void increaseStock(List<CartDTO> cartDTOS) {
        for (CartDTO cartDTO : cartDTOS) {
            //现根据id查出商品现有库存 判断商品是否存在
            ProductInfo productInfoInDatebase = productInfoMapper.selectByPrimaryKey(cartDTO.getProductId());
            if (productInfoInDatebase == null) {
                throw new SaleException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //原库存
            Integer oldStock = productInfoInDatebase.getProductStock();
            if (oldStock < 0) {
                throw new SaleException(ResultEnum.OLDSTOCK_NOT_ENOUGH);
            }
            //增加后库存
            // TODO: 2019/5/29 此处加库存应该加锁
            Integer newStock = oldStock + cartDTO.getProducQquantity();
            if (newStock < 0) {
                throw new SaleException(ResultEnum.STOCK_NOT_ENOUGH);
            }
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductStock(newStock);
            productInfo.setProductId(cartDTO.getProductId());
            int result = productInfoMapper.updateByPrimaryKeySelective(productInfo);
            if (result <= 0) {
                throw new SaleException(ResultEnum.STOCK_INCREASE_FAIL);
            }
        }
    }
}
