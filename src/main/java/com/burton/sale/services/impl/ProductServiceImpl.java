package com.burton.sale.services.impl;

import com.burton.sale.beans.ProductInfo;
import com.burton.sale.commons.PageRequest;
import com.burton.sale.commons.StockBusiness;
import com.burton.sale.daos.ProductInfoMapper;
import com.burton.sale.daos.examples.ProductInfoExample;
import com.burton.sale.dtos.CartDTO;
import com.burton.sale.enums.ProductStatusEnum;
import com.burton.sale.enums.ResultEnum;
import com.burton.sale.exceptions.SaleException;
import com.burton.sale.services.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.burton.sale.enums.ResultEnum.STOCK_NOT_ENOUGH;

/**
 * @author Burton
 * @title: ProductServiceImpl
 * @projectName sale
 * @description: product Service实现
 * @date 2019/5/27 14:09
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private StockBusiness stockBusiness;

    @Override
    public ProductInfo getProduct(Integer productId) {
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(String.valueOf(productId));
        return productInfo;
    }

    /**
     * 全部上架商品列表
     *
     * @return
     */
    @Override
    public List<ProductInfo> listUProducts() {
        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andProductStatusEqualTo(ProductStatusEnum.UP.getProductStatusCode());
        List<ProductInfo> upProductInfos = productInfoMapper.selectByExample(example);
        return upProductInfos;
    }

    /**
     * 展示全部商品信息
     * 分页显示
     * @param pageRequest
     * @return
     */
    @Override
    public PageInfo<ProductInfo> listAllProducts(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdIsNotNull();
        List<ProductInfo> productInfos = productInfoMapper.selectByExample(example);
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(productInfos);
        return pageInfo;
    }

    /**
     * 保存商品
     * @param productInfo
     * @return
     */
    @Override
    public ProductInfo savaProduct(ProductInfo productInfo) {
        int insertResult = productInfoMapper.insertSelective(productInfo);
        if (insertResult == 1) {
            return productInfo;
        }
        return null;
    }

    /**
     * 加库存
     * @param cartDTOS
     */
    @Override
    public void increaseStock(List<CartDTO> cartDTOS) {
        stockBusiness.increaseStock(cartDTOS);
    }

    /**
     * 减库存
     * @param cartDTOS
     */
    @Override
    public void decreaseStock(List<CartDTO> cartDTOS) {
        stockBusiness.decreaseStock(cartDTOS);
    }
}
