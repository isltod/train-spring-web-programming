package scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public SingletonBean singletonBean() {
        return new SingletonBean();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Scope("prototype")
    public PrototypeBean prototypeBean() {
        return new PrototypeBean();
    }

    // 이거 정말 봐도봐도 이해가 안가는데...
    @Bean(destroyMethod = "destroy")
    public FactoryBean factoryBean() {
        return FactoryBean.getInstance();
    }
}
