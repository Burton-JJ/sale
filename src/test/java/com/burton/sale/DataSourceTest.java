package com.burton.sale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Random;
import java.util.UUID;

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

    @Test
    public void randomTest() {
        Random random = new Random();
        for (int i = 0; i<20; i++) {
            int j = random.nextInt(7);
            System.out.println(j);
        }
    }

    @Test
    public void testUUID() {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        System.out.println(s);

    }


}
