package wiring;

import org.springframework.stereotype.Component;

@Component("bean11")
public class Bean1 {
    public String getValue() {
        return "Bean1";
    }
}
