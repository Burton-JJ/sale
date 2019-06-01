package com.burton.sale.services;

import com.burton.sale.beans.ProductInfo;
import com.burton.sale.commons.PageRequest;
import com.burton.sale.dtos.CartDTO;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;

import java.util.List;

/**
 * @author Burton
 * @title: ProductService
 * @projectName sale
 * @description:
 * @date 2019/5/27 11:49
 */
public interface ProductService {

    ProductInfo getProduct(Integer id);

    /**
     * 全部上架商品列表
     * @return
     */
    List<ProductInfo> listUProducts();

    /**
     * 展示全部商品信息
     * 分页显示
     * @param pageRequest
     * @return
     */
    PageInfo<ProductInfo> listAllProducts(PageRequest pageRequest);

    /**
     * 保存商品
     * @param productInfo
     * @return
     */
    ProductInfo savaProduct(ProductInfo productInfo);

    /**
     * 加库存
     */
    void increaseStock(List<CartDTO> cartDTOS);

    /**
     * 减库存
     */
    void decreaseStock(List<CartDTO> cartDTOS);



}
