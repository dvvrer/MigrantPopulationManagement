package com.xmut303.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/4/13 14:32
 */

@Configuration
@ComponentScan("com.xmut303.service")
@Import({MyBatisConfig.class,JdbcConfig.class})//导入其他配置类
public class SpringConfig {
}
