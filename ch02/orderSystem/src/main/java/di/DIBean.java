package di;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class DIBean {
    private String strField;
    private List<String> listField;
    private Set<String> setField;
    private Map<String, String> mapField;
    private Properties propsField;
    private String nullFiled;
    private String spEL;

    public DIBean() {
    }

    public DIBean(String strField) {
        this.strField = strField;
        System.out.println("추가 생성자에서 strField = '" + strField + "' 값이 주입되었습니다.");
    }

    public void setStrField(String strField) {
        this.strField = strField;
        System.out.println("세터에서 strField = '" + strField + "' 값이 주입되었습니다.");
    }

    public void setListField(List<String> listField) {
        this.listField = listField;
        System.out.println("세터에서 주입된 List: " + listField.toString());
    }

    public void setSetField(Set<String> setField) {
        this.setField = setField;
        System.out.println("세터에서 주입된 Set: " + setField.toString());
    }

    public void setMapField(Map<String, String> mapField) {
        this.mapField = mapField;
        System.out.println("세터에서 주입된 Map: " + mapField.toString());
    }

    public void setPropsField(Properties propsField) {
        this.propsField = propsField;
        System.out.println("세터에서 주입된 Properties: " + propsField.toString());
    }

    public void setNullFiled(String nullFiled) {
        this.nullFiled = nullFiled;
        if (nullFiled == null) {
            System.out.println("세터에서 Null 값이 주입되었습니다.");
        }
    }

    public void setSpEL(String spEL) {
        this.spEL = spEL;
        System.out.println("세터에서 주입된 spEL: " + spEL.toString());
    }
}
