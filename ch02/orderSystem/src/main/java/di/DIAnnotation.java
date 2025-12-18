package di;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DIAnnotation {
    @Value("기본 값")
    private String defaultValue;

    @Value("true")
    private Boolean booField;

    @Value("10")
    private Integer intField;

    @Value("${java.home}")
    private String javaHome;

    @Value("#{otherBean.strField}")
    private String otherStrField;

    @Value("${priority:normal}")
    private String priority;
    @Value("${priority1:normal}")
    private String priority1;

    // 값 목록을 받을 때는 배열, 리스트, 집합 중 가능
    @Value("${values}")
    private String[] strValues;
    @Value("#{'${values}'.split(',')}")
    private List<String> listValues;
    @Value("#{'${values}'.split(',')}")
    private Set<String> setValues;

    @Value("#{${maps}}")
    private Map<String, Integer> mapValues;

    public DIAnnotation() {
        // 어노테이션으로 지정한 값은 생성자에서는 Null이다...
        System.out.println("기본 생성자에서 본 defaultValue: " + this.defaultValue);
    }

    public DIAnnotation(String defaultValue) {
        this.defaultValue = defaultValue;
        System.out.println("추가 생성자에서 본 defaultValue: " + this.defaultValue);
    }

    public String getDefaultValue() {
        return defaultValue;
    }
    public Boolean getBooField() {
        return booField;
    }
    public Integer getIntField() {
        return intField;
    }
    public String getJavaHome() {
        return javaHome;
    }
    public String getOtherStrField() {
        return otherStrField;
    }
    public String getPriority() {
        return priority;
    }
    public String getPriority1() {
        return priority1;
    }
    public String[] getStrValues() {
        return strValues;
    }
    public List<String> getListValues() {
        return listValues;
    }
    public Set<String> getSetValues() {
        return setValues;
    }
    public Map<String, Integer> getMapValues() {
        return mapValues;
    }
}
