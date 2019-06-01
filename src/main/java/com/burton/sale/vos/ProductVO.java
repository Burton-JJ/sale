package com.burton.sale.vos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Burton
 * @title: ProductVO
 * @projectName sale
 * @description: 返回给前端的json 一个类目下所有商品（类目+商品）
 * @date 2019/5/28 12:10
 */
@Getter
@Setter
public class ProductVO {

    // TODO: 2019/5/28 换成Gson注解
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
