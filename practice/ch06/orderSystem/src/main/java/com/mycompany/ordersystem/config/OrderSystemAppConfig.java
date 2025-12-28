package com.mycompany.ordersystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@PropertySource("classpath:application.properties")
@ComponentScan("com.mycompany.ordersystem")
public class OrderSystemAppConfig implements WebMvcConfigurer {
    // 일단 JSP 사용...
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    // 정적 HTML 파일 등의 위치를 지정하는 거라고...
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(driver + "-------------------------------------------------");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/styles/**").addResourceLocations("/WEB-INF/styles/");
    }
}
