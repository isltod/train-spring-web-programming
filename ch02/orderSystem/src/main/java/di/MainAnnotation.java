package di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class MainAnnotation {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        DependentBean dependentBean = context.getBean(DependentBean.class);

        // 어노테이션으로 값 지정 실습들...
        DIAnnotation diAnnotation = context.getBean(DIAnnotation.class);
        // 빈 생성 후 컨텍스트에서 인스턴스를 불러오면 값이 들어가 있다...
        System.out.println("생성 후 컨텍스트에서 불러 온 defaultValue: " + diAnnotation.getDefaultValue());
        System.out.println("생성 후 컨텍스트에서 불러 온 booField: " + diAnnotation.getBooField().getClass().getName());
        System.out.println("생성 후 컨텍스트에서 불러 온 intField: " + diAnnotation.getIntField().getClass().getName());
        System.out.println("생성 후 컨텍스트에서 불러 온 javaHome: " + diAnnotation.getJavaHome());
        System.out.println("생성 후 컨텍스트에서 불러 온 다른 빈의 속성 값: " + diAnnotation.getOtherStrField());
        System.out.println("속성 파일에서 불러 온 priority: " + diAnnotation.getPriority());
        System.out.println("속성 파일에 없는 priority 기본 값: " + diAnnotation.getPriority1());
        System.out.println("속성 파일에서 불러 온 values를 배열로: " + Arrays.toString(diAnnotation.getStrValues()));
        System.out.println("속성 파일에서 불러 온 values를 리스트로: " + diAnnotation.getListValues().toString());
        System.out.println("속성 파일에서 불러 온 values를 집합으로: " + diAnnotation.getSetValues().toString());
        System.out.println("속성 파일에서 불러 온 maps: " + diAnnotation.getMapValues().toString());
    }
}
