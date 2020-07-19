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

}
