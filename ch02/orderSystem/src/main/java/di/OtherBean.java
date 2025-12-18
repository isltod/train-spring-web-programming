package di;

import org.springframework.beans.factory.annotation.Value;

public class OtherBean {
    @Value("다른 빈의 속성 값")
    private String strField;
    public String getStrField() {
        return strField;
    }
}
