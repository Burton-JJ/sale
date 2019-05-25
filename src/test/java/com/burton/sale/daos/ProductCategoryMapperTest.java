package com.burton.sale.daos;

import com.burton.sale.beans.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private DataSource dataSource;

    @Test
    public void showCategoryById() throws SQLException {
        System.out.println(dataSource);
        System.out.println("数据源：" + dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println("连接信息：" + connection);
        connection.close();
        ProductCategory productCategory = productCategoryMapper.selectByPrimaryKey(1);
        System.out.println(productCategory);
    }
}