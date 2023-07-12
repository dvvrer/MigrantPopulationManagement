package com.xmut303.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/4/13 14:32
 */

@Configuration
@ComponentScan("com.xmut303.controller")
@EnableWebMvc
public class SpringMVCConfig implements WebMvcConfigurer {

    //开启静态资源释放
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {
        defaultServletHandlerConfigurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {
        viewResolverRegistry.jsp("/admin/",".jsp");
    }
    //文件解析器
    @Bean
    public MultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new ResourceInterceptor(ignoreUrl)).addPathPatterns("/**")
//                .excludePathPatterns("/js/**","/css/**","/img/**");
//    }

}
