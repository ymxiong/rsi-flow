package cc.eamon.open.flow.model;

import java.util.Date;
import java.util.Map;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2022/9/14 5:41 下午
 **/
public class People {
    private String id;
    private Integer age;
    private Map<String, Object> sons;
    private Date birthday;
    private Date workday;

    public People(String id, Integer age, Map<String, Object> sons, Date birthday, Date workday) {
        this.id = id;
        this.age = age;
        this.sons = sons;
        this.birthday = birthday;
        this.workday = workday;
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

    public Map<String, Object> getSons() {
        return sons;
    }

    public void setSons(Map<String, Object> sons) {
        this.sons = sons;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getWorkday() {
        return workday;
    }

    public void setWorkday(Date workday) {
        this.workday = workday;
    }

    @Override
    public String toString() {
        return "People{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", sons=" + sons +
                ", birthday=" + birthday +
                ", workday=" + workday +
                '}';
    }
}
