package me.seungyeol.tobisp.Chapter1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = DaoFactory.class)
class UserDaoTest {

    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    public void setUp() {
        System.out.println(this.applicationContext);
        System.out.println(this);
        this.dao =  applicationContext.getBean("userDao", UserDao.class);
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
}