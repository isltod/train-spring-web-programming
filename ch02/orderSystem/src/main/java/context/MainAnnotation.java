package context;

import com.mycompany.ordersystem.Customer;
import com.mycompany.ordersystem.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainAnnotation {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // 또는 아래 두 방법 가능하지만 위가 제일 낳네...
        //CustomerService customerService = context.getBean("customerService", CustomerService.class);
        //CustomerService customerService = (CustomerService) context.getBean("customerService");
        CustomerService customerService = context.getBean(CustomerService.class);
        List<Customer> customers = customerService.getCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
