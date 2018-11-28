package com.les.db.repository;

import com.les.db.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * @ClassName: PureJpaUserRepository
 * @Description: 使用纯JPA，注入@PersistenceUnit，线程非安全性使用createEntityManager
 * @Author: king
 * @CreateDate: 2018/11/28 12:05
 */
@Repository("PureJpaUserRepository")
@Transactional(rollbackFor = Exception.class)
public class PureJpaUserRepository implements  UserRepositoryInterface{

    @PersistenceUnit(unitName = "localContainerEntityManagerFactoryBean")
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void add(User user) {
        entityManagerFactory.createEntityManager().persist(user);
    }

    @Override
    public User findOne(long id) {
        return entityManagerFactory.createEntityManager().find(User.class, id);
    }

    @Override
    public User findOneLamda(long id) {
        return entityManagerFactory.createEntityManager().find(User.class, id);
    }

    @Override
    public User findOneFunction(long id) {
        return entityManagerFactory.createEntityManager().find(User.class, id);
    }
}
