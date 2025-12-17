package context;

import com.mycompany.ordersystem.Customer;
import com.mycompany.ordersystem.CustomerService;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class MainXML {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 위 방식 외에도 아래처럼도 한다는데, 그냥 위가 간단하고 좋은데?
        //GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        //new XmlBeanDefinitionReader(context).loadBeanDefinitions("beans.xml");
        //context.refresh();
        CustomerService customerService = context.getBean(CustomerService.class);
        List<Customer> customers = customerService.getCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
