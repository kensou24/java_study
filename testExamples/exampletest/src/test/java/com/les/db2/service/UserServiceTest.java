package com.les.db2.service;

import com.les.db2.DbApplication;
import com.les.db2.dao.UserRepository;
import com.les.db2.entity.QUser;
import com.les.db2.entity.User;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @ClassName: UserServiceTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/29 13:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DbApplication.class)
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    private JPAQueryFactory jpaQueryFactory;

    @PostConstruct
    public void init()
    {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Test
    public void testQueryDSL()
    {
        QUser user = QUser.user;

        //基础使用
        BooleanExpression idIsNull = user.id.isNotNull();
        BooleanExpression nameLikeking = user.name.like("king1");
        BooleanExpression nameContainking = user.name.containsIgnoreCase("king");

        //断言的连接
        BooleanExpression newBoolean = idIsNull.or(nameContainking).and(nameContainking);

        //构建本地集合
        final List<User> userList = new ArrayList<>();
        IntStream.range(0, 100).forEach((i)->{
            userList.add(new User(i, "king"+i, "123"));
        });

        IntStream.range(101, 200).forEach((i)->{
            userList.add(new User(i, "wang"+i, "123"));
        });

        userRepository.saveAll(userList);

        List<User> userList1 = jpaQueryFactory.selectFrom(user).where(newBoolean).fetch();
        userList1.stream().forEach((userBean)->{log.info(userBean.toString());});

    }
}