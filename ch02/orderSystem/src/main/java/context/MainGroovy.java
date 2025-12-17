package context;

import com.mycompany.ordersystem.Customer;
import com.mycompany.ordersystem.CustomerService;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

import java.util.List;

public class MainGroovy {
    public static void main(String[] args) {
        ApplicationContext context = new GenericGroovyApplicationContext("beans.groovy");
        // 여기도 위 대신 아래처럼도 한다지만, 압도적으로 위가 낳네...
        //GenericApplicationContext context = new GenericApplicationContext();
        //new GroovyBeanDefinitionReader(context).loadBeanDefinitions("beans.groovy");
        //context.refresh();
        CustomerService customerService = context.getBean(CustomerService.class);
        List<Customer> customers = customerService.getCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
