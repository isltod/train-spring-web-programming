package wiring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 여기서 모든 빈을 다 명시적으로 선언하면 아래 두 어노테이션은 없어도 작동한다.
// 하지만 bean1처럼 여기에 적지 않고 그냥 클래스 선언 위에 @Component 하려면 아래 두 어노테이션이 필요하다...
@Configuration
@ComponentScan("wiring")
public class AppConfig {
    // 여기서 정한 이름은 Qualifier 태그와 매칭
    //@Bean("bean11")
    //public Bean1 bean1() {
    //    return new Bean1();
    //}

    // 여기서 빈은 만들지만 Bean1을 넣지는 않았다...
    @Bean
    public Bean2 bean2() {
        return new Bean2();
    }

    @Bean
    public Bean3 bean3(Bean1 bean1) {
        return new Bean3(bean1);
    }
}
