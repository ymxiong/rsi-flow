package cc.eamon.open.flow.example.remote.service;
import cc.eamon.open.flow.example.remote.entity.User;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 16:08:18
 */
public class UserService {

    public User getUserByName(String name) {
        if (name.equals("小朱")){
            return new User();
        }else {
            return null;
        }
    }

    public User updateAge(User user, int newAge){
        user.setAge(newAge);
        return user;
    }

    public String getUserById(String id) {
        if (id.equals("123456")){
            return "小朱";
        }else {
            return null;
        }
    }
}
