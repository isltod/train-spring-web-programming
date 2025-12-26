package com.mycompany.ordersystem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
// @ComponentScan(basePackages = {"com.mycompany.ordersystem.customer",
//         "com.mycompany.ordersystem.product",
//         "com.mycompany.ordersystem.inventory",
//         "com.mycompany.ordersystem.order"})
// 위에 보다는 아래 방식이 더 유연할 듯...
@ComponentScan(basePackages = "com.mycompany.ordersystem",
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootContextConfig {
}
