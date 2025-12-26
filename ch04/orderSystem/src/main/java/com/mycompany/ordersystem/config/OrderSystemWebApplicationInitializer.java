package com.mycompany.ordersystem.config;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.jspecify.annotations.Nullable;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GroovyWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

// 근데 기껏 설명해놓고 이런거 다 필요없다...
// public class OrderSystemWebApplicationInitializer implements WebApplicationInitializer {
//     @Override
//     public void onStartup(ServletContext servletContext) throws ServletException {
//
//         try {
//             System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
//         } catch (UnsupportedEncodingException e) {
//             throw new RuntimeException(e);
//         }
//
//         // XmlWebApplicationContext context = new XmlWebApplicationContext();
//         // context.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");
//
//         // 근데 루트를 나중에 만들어? 또는 루트가 필수가 아니라 선택이야? 맥락이 좀...
//         // XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
//         // rootContext.setConfigLocation("/WEB-INF/app-context.xml");
//         // servletContext.addListener(new ContextLoaderListener(rootContext));
//
//         AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//         context.register(OrderSystemAppConfig.class);
//
//         DispatcherServlet servlet = new DispatcherServlet(context);
//         ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
//         registration.setLoadOnStartup(1);
//         registration.addMapping("/");
//
//         // 근데 이건 만들어는 놨는데, 뭐에 쓰이는 거냐?
//         AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//         rootContext.register(OrderSystemAppConfig.class);
//         servletContext.addListener(new ContextLoaderListener(rootContext));
//
//         System.out.println("일단 여기까지...");
//     }
// }

public class OrderSystemWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?> [] getRootConfigClasses() {
        // return new Class<?>[]{RootContextConfig.class};
        // 기껏 해놓고 루트 컨텍스트도 안쓴다고...
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{OrderSystemAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}