package context;

import com.mycompany.ordersystem.CustomerRepository;
import com.mycompany.ordersystem.CustomerRepositoryImpl;
import com.mycompany.ordersystem.CustomerService;
import com.mycompany.ordersystem.CustomerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.mycompany.ordersystem")
public class AppConfig {
    // 빈 생성 방법 1, value는 삭제 가능
    //@Bean(value = "customerRepository")
    @Bean
    public CustomerRepository customerRepository() {
        return new CustomerRepositoryImpl();
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerServiceImpl(customerRepository());
        // 위 방식 대신 아래처럼도 가능하다지만...위가 낫네...
        //CustomerServiceImpl customerService = new CustomerServiceImpl();
        //customerService.setCustomerRepository(customerRepository());
        //return customerService;
    }
}
