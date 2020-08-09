package me.mw.springboard;

import me.mw.springboard.user.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Configuration
public class Runner implements ApplicationRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {


        // 테이블에있는 데이터 뽑아오기 리턴은 LIST형 SQL문에서 매핑시켜줘야함
   /*     String SQL="SELECT id,password,title,contents from account";
        List<Account> accountList=jdbcTemplate.query(SQL, (rs, rowIndex) -> {
            Account account=new Account();
            account.setId(rs.getString("id"));
            account.setPassword(rs.getString("password"));
            account.setTitle(rs.getString("title"));
            account.setContents(rs.getString("contents"));
            return account;
        });*/
        //where 절은 앞의 문장의 데이터를 정확하게 설명 하기위함

        //테이블에서 데이터를 읽어옴
  /*      String SQL = "SELECT id FROM account";
        String id = jdbcTemplate.queryForObject(SQL,String.class);
        System.out.print(id.);
*/
        //set[열] whare[열] // Where 어떤열에있는 데이터 값이 ~일때 set 열에있는 데이터값을 ~로 설정한다
  /*      SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd" + " HH:mm:ss");
        Date date1 = new Date();
        String formatDate=format1.format(date1);
        String SQL2="update account set account_date = ? where account_date is NULL ";
        jdbcTemplate.update(SQL2,formatDate);
*/
        //하나의 행 삭제
/*        String SQL3="DELETE FROM account where id='김명우'";
        jdbcTemplate.update(SQL3);*/

    }
}
