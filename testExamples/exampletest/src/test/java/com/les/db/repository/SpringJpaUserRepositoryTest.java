package com.les.db.repository;

import com.les.db.DBConfig;
import com.les.db.entity.QUser;
import com.les.db.entity.User;
import com.les.db.entity.UserDTO;
import com.les.db.service.UserService;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.criterion.Projection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @ClassName: SpringJpaUserRepositoryTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/28 13:16
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DBConfig.class)
@Slf4j
public class SpringJpaUserRepositoryTest {

    @Autowired
    SpringJpaUserRepository2 springJpaUserRepository;

    @Test
    public void findUserByName() {
        /*
        List<User> userList = springJpaUserRepository.findUsersByName("king");
        List<User> userList2 = springJpaUserRepository.findUsersMy("king");
        log.info("get result ==" + userList.toString());
        log.info("get result ==" + userList2.toString());
        assertEquals(userList.size(), userList2.size());
        */

        QUser user = QUser.user;
        Iterable<User> userIterable = springJpaUserRepository.findAll(user.name.eq("king"));
        log.info("*******************");
        for (User user1 : userIterable) {
            log.info(user1.toString());
        }
    }


}