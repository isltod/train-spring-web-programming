package scope;

public class SingletonBean {
    public SingletonBean() {
        System.out.println("Singleton Bean Created");
    }

    public void init() {
        System.out.println("Singleton Bean Initialized");
    }

    public void destroy() {
        System.out.println("Singleton Bean Destroyed");
    }
}
