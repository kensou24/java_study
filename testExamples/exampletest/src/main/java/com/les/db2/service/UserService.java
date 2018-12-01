package com.les.db2.service;

import com.google.common.collect.Lists;
import com.les.db2.dao.UserRepository;
import com.les.db2.entity.User;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserService
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/29 8:51
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser()
    {
        final List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
           list.add(user);
        });
        return list;
    }

    public Iterable<User> search(Predicate predicate)
    {
        Iterable<User> all = userRepository.findAll(predicate);
        return all;
    }

}
