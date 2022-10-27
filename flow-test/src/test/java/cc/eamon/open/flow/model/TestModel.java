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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Map<String, Object> buildMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", this.name);
        map.put("age", this.age);
        map.put("money", this.money);
        return map;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
