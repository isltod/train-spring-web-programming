package com.mycompany.ordersystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// 스캔만 한다고 되는건 아닌듯...Service, Repository 다 붙여줘야 찾아 넣네...
@ComponentScan("com.mycompany.ordersystem")
public class AppConfig {
}
