package com.burton.sale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * @author Burton
 * @title: DataSourceTest
 * @projectName sale
 * @description: TODO
 * @date 2019/5/26 0:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void datasourceTest() {
        System.out.println(this.dataSource);
    }

}
