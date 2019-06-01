package com.burton.sale.vos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Burton
 * @title: ProductInfoVO
 * @projectName sale
 * @description: 返回给前端json中的商品信息 productVO中一部分（商品）
 * @date 2019/5/28 12:13
 */
@Getter
@Setter
public class ProductInfoVO {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
