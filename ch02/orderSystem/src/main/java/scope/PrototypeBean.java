package scope;

public class PrototypeBean {
    public PrototypeBean() {
        System.out.println("PrototypeBean Created");
    }

    public void init() {
        System.out.println("PrototypeBean Bean Initialized");
    }

    public void destroy() {
        System.out.println("PrototypeBean Bean Destroyed");
    }
}
