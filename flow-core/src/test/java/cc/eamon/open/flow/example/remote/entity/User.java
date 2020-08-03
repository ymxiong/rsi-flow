package cc.eamon.open.flow.example.remote.entity;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 16:08:56
 */
public class User {

    private String id = "123456";

    private String name = "小朱";

    private int age = 22;

    private String gender = "男";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }
}
