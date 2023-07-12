package com.xmut303.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/4/13 14:49
 */

@PropertySource("classpath:db.properties")
public class JdbcConfig {

    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.driver}")
    private String driver;
    @Value("${mysql.username}")
    private String username;
    @Value("${mysql.password}")
    private String password;

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setDriverClassName(driver);
        dataSource.setPassword(password);
        return dataSource;
    }
}
