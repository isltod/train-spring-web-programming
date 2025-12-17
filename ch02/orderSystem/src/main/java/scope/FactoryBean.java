package scope;

public class FactoryBean {
    public FactoryBean() {
        System.out.println("Factory Bean Created");
    }

    private static class Factory {
        static final  FactoryBean INSTANCE = new FactoryBean();
    }

    public static FactoryBean getInstance() {
        return Factory.INSTANCE;
    }

    public void destroy() {
        System.out.println("Factory Bean Destroyed");
    }
}
