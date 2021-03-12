package me.seungyeol.tobisp.Chapter1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/springbook?serverTimezone=Asia/Seoul");
        dataSource.setUsername("spring");
        dataSource.setPassword("book");
        return dataSource;
    }

    @Bean
    public JdbcContext jdbcContext() {
        return new JdbcContext(dataSource());
    }
    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        return userDao;
    }

//    public AccountDao accountDao() {
//        AccountDao accountDao = new AccountDao(getConnectionMaker());
//    }

    @Bean
    public DConnectionMaker getConnectionMaker() {
        return new DConnectionMaker();
    }
}
