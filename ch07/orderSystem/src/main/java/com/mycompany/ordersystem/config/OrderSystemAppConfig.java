package com.mycompany.ordersystem.config;

import com.mycompany.ordersystem.converter.DateFormatAnnotationFormatterFactory;
import com.mycompany.ordersystem.converter.DateToStringTypeConverter;
import com.mycompany.ordersystem.converter.StringToDateTypeConverter;
import com.mycompany.ordersystem.views.PdfProductReport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com.mycompany.ordersystem")
public class OrderSystemAppConfig implements WebMvcConfigurer {
    // 일단 JSP 사용...
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.beanName();
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    // 정적 HTML 파일 등의 위치를 지정하는 거라고...
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/styles/**").addResourceLocations("/WEB-INF/styles/");
    }

    @Bean
    public PdfProductReport pdfProductReport() {
        return new PdfProductReport();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 근데 날자를 문자열로 바꿔서 표현하려면 이런거 다 필요없고 클래스 필드에 어노테이션 붙여서 끝이라고...
        // registry.addConverter(new StringToDateTypeConverter());
        // registry.addConverter(new DateToStringTypeConverter());
        // 이건 너무 복잡한데...이렇게까지 해야 하나?
        // registry.addFormatterForFieldAnnotation(new DateFormatAnnotationFormatterFactory());
    }
}
