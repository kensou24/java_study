package com.les.db2.dao;

import com.les.db2.entity.QUser;
import com.les.db2.entity.User;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: UserRepository
 * @Description: 使用QuerydslBinderCustomizer进行自定义
 * @Author: king
 * @CreateDate: 2018/11/29 8:50
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>, QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {
    @Override
    default void customize(QuerydslBindings bindings, QUser root)
    {
        bindings.bind(root.name).first(StringExpression::containsIgnoreCase);
    }

}
