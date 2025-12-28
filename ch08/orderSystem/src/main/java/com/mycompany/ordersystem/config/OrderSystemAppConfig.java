package com.mycompany.ordersystem.config;

import com.mycompany.ordersystem.converter.DateFormatAnnotationFormatterFactory;
import com.mycompany.ordersystem.converter.DateToStringTypeConverter;
import com.mycompany.ordersystem.converter.StringToDateTypeConverter;
import com.mycompany.ordersystem.views.PdfProductReport;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Locale;

@Configuration
@EnableWebMvc
@PropertySource("classpath:application.properties")
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

    // 파일 업로드
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    // 국제화
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // 실제 서버에서는 -1로 캐시를 영속적으로 가진다고...
        messageSource.setCacheSeconds(1);
        messageSource.setBasenames("classpath:message", "classpath:validation");
        return messageSource;
    }

    // 아래 두 개는 검증 메시지 국제화
    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Override
    public @Nullable Validator getValidator() {
        return validator();
    }

    // 아래 세 개는 클라이언트 로케일 반응 관련
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.KOREAN);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    // 데이터 연결 부분
    // 이게 있어야 밑에 @Value 어노테이션이 작동하는 모양인데...
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() throws IOException {
        System.out.println(driver + "----------------------------------------------------------");
        // 요건 @Value 작동 안해서 application.properties 실제 경로 찾아본 코드
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:application.properties");
        System.out.println("resource = " + resource.exists());
        System.out.println("resource.getFilename() = " + resource.getURI().toString());
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // dataSource.setDriverClassName(driver);
        // dataSource.setUrl(url);
        // dataSource.setUsername(username);
        // dataSource.setPassword(password);
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws IOException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
}
