package com.mycompany.ordersystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
// @ComponentScan("com.mycompany.ordersystem.controller")
// 위처럼 쓰면 간단하긴 한데, 패키지 경로로 고정되서 아래가 더 유연할 것 같긴 하다...
@ComponentScan(basePackages = {"com.mycompany.ordersystem"},
               useDefaultFilters = false,
               includeFilters = {@ComponentScan.Filter(Controller.class)})
public class OrderSystemAppConfig implements WebMvcConfigurer {
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    // 위에 거나 아래 거나 둘 중 하나를 하면 되는데...
    // @Bean
    // public ViewResolver viewResolver() {
    //     InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    //     viewResolver.setPrefix("/WEB-INF/views/");
    //     viewResolver.setSuffix(".jsp");
    //     return viewResolver;
    // }

    // 정적 HTML 파일 등의 위치를 지정하는 거라고...
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/styles/**").addResourceLocations("/WEB-INF/styles/");
    }
}
