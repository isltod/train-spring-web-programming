package wiring;

public class Bean3 {
    private Bean1 bean1;

    public Bean1 getBean1() {
        return bean1;
    }

    public Bean3(Bean1 bean1) {
        this.bean1 = bean1;
    }
}
