package cc.eamon.open.flow.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestModel {
    private String name;
    private int age;
    private double money;

    public TestModel(String name, int age, double money) {
        this.name = name;
        this.age = age;
        this.money = money;
    }

    public Map<String, Object> buildMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", this.name);
        map.put("age", this.age);
        map.put("money", this.money);
        return map;
    }
}
