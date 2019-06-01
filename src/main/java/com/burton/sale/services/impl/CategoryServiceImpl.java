package com.burton.sale.services.impl;

import com.burton.sale.beans.ProductCategory;
import com.burton.sale.daos.ProductCategoryMapper;
import com.burton.sale.daos.examples.ProductCategoryExample;
import com.burton.sale.daos.myMapperInterfaces.MyProductCategoryMapper;
import com.burton.sale.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Burton
 * @title: CategoryServiceImpl
 * @projectName sale
 * @description: CategoryServiceImpl
 * @date 2019/5/26 22:54
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private MyProductCategoryMapper myProductCategoryMapper;

    /**
     * 根据入参id查询单个类目
     * @param categoryId
     * @return
     */
    @Override
    public ProductCategory getCategory(Integer categoryId) {
        ProductCategory productCategory = productCategoryMapper.selectByPrimaryKey(categoryId);
        return productCategory;
    }

    /**
     * 查询全部类目
     * @return
     */
    @Override
    public List<ProductCategory> listAllCategory() {
        ProductCategoryExample example = new ProductCategoryExample();
        ProductCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdIsNotNull();
        List<ProductCategory> productCategories = productCategoryMapper.selectByExample(example);
        return productCategories;
    }

    /**
     * 根据入参id列表查询类目列表
     * @param ids
     * @return
     */
    @Override
    public List<ProductCategory> listCatogoryByIds(List<Integer> ids) {
        ProductCategoryExample example = new ProductCategoryExample();
        ProductCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdIn(ids);
        List<ProductCategory> productCategories = productCategoryMapper.selectByExample(example);
        return productCategories;
    }

    /**
     * 保存类目
     * @param productCategory
     * @return
     */
    @Override
    public ProductCategory savaCategory(ProductCategory productCategory) {
        int result = productCategoryMapper.insertSelective(productCategory);
        //插入成功，返回入参的类目信息
        if (result == 1) {
            return productCategory;
        }
        //插入失败，返回null
        return null;
    }

    /**
     * 根据类目类型获取类目
     *
     * @param categoryType
     * @return
     */
    @Override
    public ProductCategory getCategoryByType(Integer categoryType) {
        //方法一，使用逆向工程生成的方法
//        ProductCategoryExample example = new ProductCategoryExample();
//        ProductCategoryExample.Criteria criteria = example.createCriteria();
//        criteria.andCategoryTypeEqualTo(categoryType);
//        List<ProductCategory> productCategories = productCategoryMapper.selectByExample(example);
//        return productCategories.get(0);
        //方法二，自己写sql
        ProductCategory productCategory = myProductCategoryMapper.selectCategoryByType(1);
        return productCategory;
    }

    /**
     * 根据类目类型列表获取多个类目
     *
     * @param categoryTypes
     * @return
     */
    @Override
    public List<ProductCategory> listCategorysByTypes(List<Integer> categoryTypes) {
        ProductCategoryExample example = new ProductCategoryExample();
        ProductCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryTypeIn(categoryTypes);
        List<ProductCategory> productCategories = productCategoryMapper.selectByExample(example);
        return productCategories;
    }
}
