package me.seungyeol.tobisp.Chapter1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = DaoFactory.class)
class UserDaoJdbcTest {

    @Autowired
    private UserDao dao;
    @Autowired
    DataSource dataSource;

    private User user1;
    private User user2;
    private User user3;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    public void setUp() {
        System.out.println(this.applicationContext);
        System.out.println(this);
        this.dao =  applicationContext.getBean("userDao", UserDaoJdbc.class);
        this.user1 = new User("gyumee", "박성철", "springno1");
        this.user2 = new User("leegw700", "이길원", "springno2");
        this.user3 = new User("bumjin", "박범진", "springno3");
    }


    @Test
    public void dcount() throws SQLException {


        dao.deleteAll();
        Assertions.assertThat(dao.getCount()).isEqualTo(0);


        dao.add(user1);
        Assertions.assertThat(dao.getCount()).isEqualTo(1);
        dao.add(user2);
        Assertions.assertThat(dao.getCount()).isEqualTo(2);
        dao.add(user3);
        Assertions.assertThat(dao.getCount()).isEqualTo(3);
    }

    @Test
    public void addAndGet() throws SQLException {


        dao.deleteAll();
        Assertions.assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        dao.add(user2);
        Assertions.assertThat(dao.getCount()).isEqualTo(2);

        User userget1 = dao.get(user1.getId());
        Assertions.assertThat(userget1.getName()).isEqualTo(user1.getName());


        User userget2 = dao.get(user2.getId());
        Assertions.assertThat(userget2.getName()).isEqualTo(user2.getName());

    }

    @Test
    void getUserFailure() throws SQLException {


        dao.deleteAll();
        Assertions.assertThat(dao.getCount()).isEqualTo(0);
        assertThrows(EmptyResultDataAccessException.class, () -> dao.get("unknown_id"));
    }

    @Test
    public void getAll() throws SQLException {
        dao.deleteAll();

        List<User> users0 = dao.getAll();
        assertThat(users0.size()).isEqualTo(0);

        dao.add(user1); // Id : gyumee
        List<User> users1 = dao.getAll();
        assertThat(users1.size()).isEqualTo(1);
        checkSameUser(user1, users1.get(0));


        dao.add(user2); // Id : leegw700
        List<User> users2 = dao.getAll();
        assertThat(users2.size()).isEqualTo(2);
        checkSameUser(user1, users2.get(0));
        checkSameUser(user2, users2.get(1));


        dao.add(user3); // Id : bumjin
        List<User> users3 = dao.getAll();
        assertThat(users3.size()).isEqualTo(3);
        checkSameUser(user3, users3.get(0)); // 알파벳 순
        checkSameUser(user1, users2.get(0));
        checkSameUser(user2, users2.get(1));
    }
    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId()).isEqualTo(user2.getId());
        assertThat(user1.getName()).isEqualTo(user2.getName());
        assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
    }

    @Test
    public void duplicateKey() {
        dao.deleteAll();

        dao.add(user1);
        assertThrows(DuplicateKeyException.class, () -> dao.add(user1));
    }

    @Test
    public void sqlExceptionTranslate() {
        dao.deleteAll();
        try {
            dao.add(user1);
            dao.add(user1);
        } catch (DuplicateKeyException ex) {
            SQLException sqlEx = (SQLException)ex.getRootCause();
            SQLExceptionTranslator set =
                    new SQLErrorCodeSQLExceptionTranslator(this.dataSource);

            assertThat(set.translate(null,null,sqlEx)).isInstanceOf(DuplicateKeyException.class);
        }
    }
}