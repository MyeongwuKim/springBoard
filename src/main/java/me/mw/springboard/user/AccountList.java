package me.mw.springboard.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountList {

    private ArrayList<Account> accountList;

    public AccountList(){
        accountList=new ArrayList<>();
    }
    public void setAccountList(Account account){
         accountList.add(0,account);
    }
    public void deleteAccount(int titleNumber){
        accountList.remove(titleNumber);
    }
    public List<Account> getAccountList(){
        return accountList;
    }
    public Account getAccountInfo(int index){
        return accountList.get(index);
    }
}
