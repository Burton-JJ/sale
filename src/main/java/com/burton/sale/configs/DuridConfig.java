package com.burton.sale.configs;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Burton
 * @title: DuridConfig
 * @projectName sale
 * @description: 为了使yml配置文件中druid配置文件生效
 * @date 2019/5/26 2:14
 */
@Configuration
public class DuridConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }
}
