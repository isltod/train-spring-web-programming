package di;

public class DependentBean {
    private InjectedBean injectedBean;

    public DependentBean() {
        System.out.println("DependentBean 기본 생성자");
    }

    public DependentBean(InjectedBean injectedBean) {
        System.out.println("DependentBean 추가 생성자");
        System.out.println("추가 생성자에서 injectedBean이 주입됩니다.");
        this.injectedBean = injectedBean;
    }

    public void setInjectedBean(InjectedBean injectedBean) {
        System.out.println("세터에서 InjectedBean이 주입됩니다.");
        this.injectedBean = injectedBean;
    }
}
