package com.burton.sale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Burton
 * @title: LoggerTest
 * @projectName sale
 * @description: TODO
 * @date 2019/5/23 2:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {

    private Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1() {
        String name = "burton";
        String password = "123456";
        logger.debug("debug...");
        logger.info("info...name:" + name + ",password:"+ password);
        logger.info("info...name:{},password:{}", name, password);
        logger.error("error...");
        logger.warn("warn...");
    }
}
