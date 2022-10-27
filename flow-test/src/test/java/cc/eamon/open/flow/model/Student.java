package cc.eamon.open.flow.model;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2022/9/14 5:41 下午
 **/
public class Student {
    private String id;
    private Integer age;
    private People people;

    public Student(String id, Integer age, People people) {
        this.id = id;
        this.age = age;
        this.people = people;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", people=" + people +
                '}';
    }
}
