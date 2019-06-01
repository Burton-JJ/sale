package com.burton.sale.daos.myMapperInterfaces;

import com.burton.sale.beans.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Burton
 * @title: MyProductCategoryMapper
 * @projectName sale
 * @description: 非逆向工程生成的类目mapper
 * @date 2019/5/28 15:17
 */
@Mapper
public interface MyProductCategoryMapper {
    /**
     * 根据类目类型获取类目 类型唯一
     * @param type
     * @return
     */
    ProductCategory selectCategoryByType(Integer type);
}
