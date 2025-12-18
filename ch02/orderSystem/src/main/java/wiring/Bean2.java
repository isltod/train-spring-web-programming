package wiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Bean2 {
    // 명시적으로 Bean1을 넣는 setter, constructor 다 없지만 자동으로 넣어진다..Bean3에 비교해서...
    // XML에서는 autowired 속성으로 정리되서 이게 필요하지 않지만, annotation config에서는 없으므로 이게 필요하다...
    //@Autowired
    //@Qualifier("bean11")
    private Bean1 bean1;

    public Bean1 getBean1() {
        return bean1;
    }

    // 이 setter는 annotation에서는 필요 없지만 XML에서는 이걸 이용하므로 필요하다...byName 또는 byType...
    @Autowired
    //@Qualifier("bean1")
    public void setBean1(Bean1 bean1) {
        this.bean1 = bean1;
    }
}
