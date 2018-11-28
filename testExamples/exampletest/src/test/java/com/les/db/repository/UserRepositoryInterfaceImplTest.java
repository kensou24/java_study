package com.les.db.repository;

import com.les.db.entity.User;
import com.les.db.DBConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @ClassName: UserRepositoryInterfaceImplTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/28 9:05
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DBConfig.class)
@Slf4j
public class UserRepositoryInterfaceImplTest {
    @Autowired
    //@Qualifier("PureJpaUserRepository")
    //@Qualifier("PureJpaUserRepository2")
    //@Qualifier("springJpaUserRepository")
    @Qualifier("UserRepositoryInterfaceImpl")
    UserRepositoryInterface userRepositoryInterface;

    @Test
    public void add() {
        User user = new User("king", "king");
        userRepositoryInterface.add(user);
    }

    @Test
    public void findOnde()
    {
        User user = userRepositoryInterface.findOne(1);
        assertEquals(user.getId(), 1);
    }

    @Test
    public void findOneLamda() {
        User user = userRepositoryInterface.findOneLamda(1);
        assertEquals(user.getId(), 1);
    }

    @Test
    public void findOneFunction() {
        User user = userRepositoryInterface.findOneFunction(1);
        assertEquals(user.getId(), 1);
    }
}