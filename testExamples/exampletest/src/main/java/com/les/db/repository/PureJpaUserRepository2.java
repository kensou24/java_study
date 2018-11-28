package com.les.db.repository;

import com.les.db.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 * @ClassName: PureJpaUserRepository
 * @Description: 使用纯JPA，线程安全性使用PersistenceContext
 * @Author: king
 * @CreateDate: 2018/11/28 12:05
 */
@Repository("PureJpaUserRepository2")
@Transactional(rollbackFor = Exception.class)
public class PureJpaUserRepository2 implements  UserRepositoryInterface{

    @PersistenceContext(unitName = "localContainerEntityManagerFactoryBean")
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findOne(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findOneLamda(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findOneFunction(long id) {
        return entityManager.find(User.class, id);
    }
}
