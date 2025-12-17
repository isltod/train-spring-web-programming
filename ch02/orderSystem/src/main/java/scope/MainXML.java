package scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainXML {
    public static void main(String[] args) {
        // 여기도 MainAnnotation 처럼...
        //ApplicationContext context = new ClassPathXmlApplicationContext("scope.xml");
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("scope.xml");
        // 그리고 이거 대신 명시적으로 끝날 때 닫아줘도 된다.
        //context.registerShutdownHook();
        // 싱글톤은 두 번 불러도 한 번만 생성되므로 출력은 한 번 뿐이다...
        SingletonBean singletonBean1 = context.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = context.getBean(SingletonBean.class);
        PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
        FactoryBean factoryBean = context.getBean(FactoryBean.class);
        context.close();
    }
}
