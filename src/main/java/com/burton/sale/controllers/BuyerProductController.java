package com.burton.sale.controllers;

import com.burton.sale.beans.ProductCategory;
import com.burton.sale.beans.ProductInfo;
import com.burton.sale.services.CategoryService;
import com.burton.sale.services.impl.CategoryServiceImpl;
import com.burton.sale.services.impl.ProductServiceImpl;
import com.burton.sale.utils.ResultVOUtil;
import com.burton.sale.vos.ProductInfoVO;
import com.burton.sale.vos.ProductVO;
import com.burton.sale.vos.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Burton
 * @title: BuyerProductController
 * @projectName sale
 * @description: 买家controller 只能看到所有上架商品
 * @date 2019/5/27 20:42
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //1.查询所有上架商品
        List<ProductInfo> upProductInfos = productService.listUProducts();
        //2.查询类目
        List<Integer> categoryTypes = new ArrayList<>();
        for (ProductInfo productInfo : upProductInfos) {
            categoryTypes.add(productInfo.getCategoryType());
        }
        //所有在线类目
        List<ProductCategory> productCategories = categoryService.listCategorysByTypes(categoryTypes);
        //3.数据拼装
        //类目+商品信息
        List<ProductVO> productVOList = new ArrayList<>();
        //一次for循环将一个类目下所有商品都集合起来
        for (ProductCategory productCategory : productCategories) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            //商品信息
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : upProductInfos) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
//                    productInfoVO.setProductId(productInfo.getProductId());
//                    productInfoVO.setProductName(productInfo.getProductName());
//                    productInfoVO.setProductDescription(productInfo.getProductDescription());
//                    productInfoVO.setProductIcon(productInfo.getProductIcon());
//                    productInfoVO.setProductPrice(productInfo.getProductPrice());
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
//        ResultVO resultVO = new ResultVO();
//        resultVO.setResponseCode(0);
//        resultVO.setResponseMsg("成功");
//        resultVO.setData(productVOList);
//        return resultVO;
    }
}
