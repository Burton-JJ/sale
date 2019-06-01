package com.burton.sale.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Burton
 * @location：com.burton.sale.dtos.CartDTO
 * @title: CartDTO
 * @projectName sale
 * @description: 购物车（商品id+商品数量）
 * @date 2019/5/29 15:38
 */
@Getter
@Setter
public class CartDTO {

    /**
     * 商品数量
     */
    private Integer producQquantity;

    /**
     * 商品id
     */
    private String productId;

    public CartDTO(Integer producQquantity, String productId) {
        this.producQquantity = producQquantity;
        this.productId = productId;
    }
}
