package wiring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAnnotation {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // Bean3는 setter에서 명시적으로 넣었지만...
        Bean3 bean3 = (Bean3)context.getBean("bean3");
        Bean1 bean1 = bean3.getBean1();
        System.out.println(bean1.getValue());
        // Bean2는 넣지도 않았는데 Autowired로 자동 삽입되어있다...
        Bean2 bean2 = context.getBean(Bean2.class);
        bean1 = bean2.getBean1();
        System.out.println(bean1.getValue());
    }
}
