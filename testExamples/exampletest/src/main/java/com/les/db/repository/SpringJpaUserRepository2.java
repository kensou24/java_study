package com.les.db.repository;

import com.les.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


import java.util.List;

/**
 * @ClassName: SpringJpaUserRepository
 * @Description: 使用SpringJPA方式,并且使用扩展UserRepositoryInterface实现高级查询
 * @Author: king
 * @CreateDate: 2018/11/28 13:10
 */

public interface SpringJpaUserRepository2 extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
    List<User> findUsersByName(String name);

    @Query("select s from User s where s.name = 'king'")
    List<User> findUsersMy(String name);
}
