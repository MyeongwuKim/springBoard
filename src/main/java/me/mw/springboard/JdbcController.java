package me.mw.springboard;

import me.mw.springboard.user.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import java.sql.ResultSet;
import java.util.List;

@Controller
public class JdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void setAccountData(Account account){
        String SQL="INSERT INTO account values(?,?,?,?,?,?,?)";
        String id=account.getId();
        String password=account.getPassword();
        String title=account.getTitle();
        String contents=account.getContents();
        String date=account.getDate();
        int title_number=account.getTitleNumber();
        String filename=account.getFilename();
        jdbcTemplate.update(SQL,id,password,title,contents,date,title_number,filename);
    }

    public void modifyAccountData(Account account){
        String SQL="UPDATE account set id=?,password=?,title=?,contents=?,file_name=? where title_number=?";
        String id=account.getId();
        String password=account.getPassword();
        String title=account.getTitle();
        String contents=account.getContents();
        int title_number=account.getTitleNumber();
        String filename=account.getFilename();
        jdbcTemplate.update(SQL,id,password,title,contents,filename,title_number);
    }

    public List<Account> getAccountData() {
        String SQL = "SELECT id,password,title,contents,write_date,title_number,file_name FROM account";
        List<Account> accountList = jdbcTemplate.query(SQL,(rs, rowIndex) -> {
            Account account = new Account();
            account.setId(rs.getString("id"));
            account.setPassword(rs.getString("password"));
            account.setTitle(rs.getString("title"));
            account.setContents(rs.getString("contents"));
            account.setDate(rs.getString("write_date"));
            account.setTitleNumber(rs.getInt("title_number"));
            account.setFilename(rs.getString("file_name"));
            return account;
        });
        return accountList;
    }
    public void deleteDataByTitleNumber(int titleNumber){
        String SQL="DELETE FROM account where title_number=?";
        jdbcTemplate.update(SQL,titleNumber);
        //UPDATE account SET title_number=title_number-1 where title_number>0
        SQL="UPDATE account set title_number = title_number-1 where title_number > ? ";
        jdbcTemplate.update(SQL,titleNumber);
    }
    public int getRowIndexCount(){
        String SQL = "select count(*) from account";
        int rowCount = jdbcTemplate.queryForObject(SQL, Integer.class);
        return rowCount;
    }
    public Account findAccountByTitleNumber(Integer titleNumber){
        String SQL = "SELECT id,password,title,contents,write_date,file_name FROM account where title_number=?";

        Account account = jdbcTemplate.queryForObject(SQL,(rs,i)->{
            Account account1 = new Account();
            account1.setId(rs.getString("id"));
            account1.setPassword(rs.getString("password"));
            account1.setTitle(rs.getString("title"));
            account1.setContents(rs.getString("contents"));
            account1.setDate(rs.getString("write_date"));
            account1.setFilename(rs.getString("file_name"));
            account1.setTitleNumber(titleNumber);

            return account1;
        },titleNumber);
        return account;
    }
}
