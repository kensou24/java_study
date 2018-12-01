package com.les.db.service;

import com.les.db.entity.QUser;
import com.les.db.entity.User;
import com.les.db.entity.UserDTO;
import com.les.db.repository.SpringJpaUserRepository2;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @ClassName: UserService
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/28 15:50
 */
@Service
@Slf4j
@Transactional(transactionManager ="annotationDrivenTransactionManager", rollbackFor = Exception.class) //transactionManager = "jpaTransactionManager",
public class UserService {

    JPAQueryFactory queryFactory;

    //@PersistenceContext(unitName="localContainerEntityManagerFactoryBean")
    @PersistenceContext(unitName="localSessionFactoryBean")
    private EntityManager entityManager;

    @PostConstruct
    public void initFactory()
    {
        queryFactory = new JPAQueryFactory(entityManager);
        System.out.println("init JPAQueryFactory successfully");
    }

    //查询某一项
    public void selectAllName()
    {
        QUser user = QUser.user;
        log.info("*******************222222");
        List<String> nameList = queryFactory.select(user.name).from(user).fetch();
        nameList.forEach(System.out::println);
        log.info("*******************222222----end");
    }

    //更新
    public void update()
    {
        QUser user = QUser.user;
        queryFactory.update(user).set(user.name, "king2").where(user.id.between(1, 2)).execute();
    }

    //删除
    public void delete()
    {
        QUser user = QUser.user;
        queryFactory.delete(user).where(user.id.between(1, 3)).execute();
    }

    //查询所有
    public void selectFromAll()
    {
        QUser user = QUser.user;
        log.info("*******************222222");
        List<User> userList = queryFactory.selectFrom(user).fetch();
        userList.forEach(System.out::println);
        log.info("*******************222222----end");
    }


    //查询转换DTO
    public void dtoTest()
    {
        QUser user = QUser.user;
        List<UserDTO> userList = queryFactory.select(Projections.constructor(UserDTO.class, user.id, user.name)).from(user).fetch();
        userList.forEach(System.out::println);
    }


    //去重
    public void selectDistinctName()
    {
        QUser user = QUser.user;
        List<String> nameList = queryFactory.selectDistinct(user.name).from(user).fetch();
        nameList.forEach(System.out::println);
    }

    //获取第一个
    public void fetchFirst()
    {
        QUser user = QUser.user;
        String name = queryFactory.selectDistinct(user.name).from(user).fetchFirst();
        log.info("first " + name);
    }

    //获取唯一一个
    public void fetchOne()
    {
        QUser user = QUser.user;
        String name = queryFactory.selectDistinct(user.name).from(user).fetchOne();
        log.info("unique one " + name);
    }

    //构建查询条件
    public void booleanBuilder()
    {
        QUser user = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(user.name.like("g2"));
        booleanBuilder.or(user.id.between(5,6));
        List<User> userList = queryFactory.select(user).from(user).where(booleanBuilder).fetch();
        userList.forEach(System.out::println);
    }
}
