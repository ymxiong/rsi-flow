package cc.eamon.open.flow.example.remote.entity;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 16:10:58
 */
public class Account {

    private String id = "654321";

    private String userId = "123456";

    private String role = "manager";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
