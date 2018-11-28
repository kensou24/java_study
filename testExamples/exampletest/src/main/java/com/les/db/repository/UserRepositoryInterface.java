package com.les.db.repository;

import com.les.db.entity.User;

/**
 * @ClassName: UserRepositoryInterface
 * @Description: 接口
 * @Author: king
 * @CreateDate: 2018/11/28 8:59
 */
public interface UserRepositoryInterface {
    void add(User user);

    User findOne(long id);
    User findOneLamda(long id);
    User findOneFunction(long id);

}
