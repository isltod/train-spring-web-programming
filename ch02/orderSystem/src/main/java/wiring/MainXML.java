package wiring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainXML {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("wiring.xml");
        Bean2 bean2 = context.getBean(Bean2.class);
        Bean1 bean1 = bean2.getBean1();
        System.out.println(bean1.getValue());
        Bean3 bean3 = context.getBean(Bean3.class);
        bean1 = bean3.getBean1();
        System.out.println(bean1.getValue());
    }
}
