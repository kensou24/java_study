package com.les.db.repository;

import com.les.db.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


/**
 * @ClassName: HibernateUserRepositoryImpl
 * @Description: 使用hibernate原生方式使用，必须开启Transactional
 * @Author: king
 * @CreateDate: 2018/11/28 10:38
 */

@Repository("HibernateUserRepository")
@Transactional(rollbackFor = Exception.class)
public class HibernateUserRepository implements UserRepositoryInterface {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(User user) {
        Serializable id = currentSession().save(user);
        return;
    }

    @Override
    public User findOne(long id) {
        return currentSession().get(User.class, id);
    }

    @Override
    public User findOneLamda(long id) {
        return currentSession().get(User.class, id);
    }

    @Override
    public User findOneFunction(long id) {
        return currentSession().get(User.class, id);
    }
}
