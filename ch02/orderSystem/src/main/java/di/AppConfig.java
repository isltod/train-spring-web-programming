package di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
// 속성 파일을 불러 쓰려면 이걸 지정해야 한다...
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    public InjectedBean injectedBean() {
        return new InjectedBean();
    }

    @Bean
    public DependentBean dependentBean() {
        // 이건 생성자에서 주입 방식
        DependentBean dependentBean = new DependentBean(injectedBean());
        // 이건 세터에서 주입 방식
        //DependentBean dependentBean = new DependentBean();
        //dependentBean.setInjectedBean(injectedBean());
        return dependentBean;
    }

    @Bean
    public DIBean diBean() {
        // 세터에서 값 주입
        //DIBean diBean = new DIBean();
        //diBean.setStrField("주입된 값");
        // 생성자에서 값 주입
        DIBean diBean = new DIBean("주입된 값");
        return diBean;
    }

    @Bean
    public DIAnnotation diAnnotation() {
        DIAnnotation diAnnotation = new DIAnnotation();
        return diAnnotation;
    }

    @Bean
    public OtherBean otherBean() {
        return new OtherBean();
    }
}
