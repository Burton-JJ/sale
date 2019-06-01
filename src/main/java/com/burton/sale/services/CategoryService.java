package com.burton.sale.services;

import com.burton.sale.beans.ProductCategory;

import java.util.List;

/**
 * @author Burton
 * @title: CategoryService
 * @projectName sale
 * @description: 类目servicr
 * @date 2019/5/26 22:31
 */
public interface CategoryService {

    /**
     * 根据入参id查询单个类目
     * @param categoryId
     * @return
     */
    ProductCategory getCategory(Integer categoryId);

    /**
     * 查询全部类目
     * @return
     */
    List<ProductCategory> listAllCategory();

    /**
     * 根据入参id列表查询类目列表
     * @param ids
     * @return
     */
    List<ProductCategory> listCatogoryByIds(List<Integer> ids);

    /**
     * 保存类目
     * @param productCategory
     * @return
     */
    ProductCategory savaCategory(ProductCategory productCategory);

    /**
     * 根据类目类型获取类目
     * @param categoryType
     * @return
     */
    ProductCategory getCategoryByType(Integer categoryType);

    /**
     * 根据类目类型列表获取多个类目
     * @param categoryTypes
     * @return
     */
    List<ProductCategory> listCategorysByTypes(List<Integer> categoryTypes);
}
