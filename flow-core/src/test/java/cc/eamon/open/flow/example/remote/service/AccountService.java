package cc.eamon.open.flow.example.remote.service;
import cc.eamon.open.flow.example.remote.entity.Account;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 16:08:18
 */
public class AccountService {

    public Account getAccountByUserId(String userId) {
        return new Account();
    }

    public int getBalance(){
        return 12345;
    }

    public String getRole(Account account){
        return account.getRole();
    }
}
