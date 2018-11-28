package com.les.db.repository;

import com.les.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: UserRepository
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/28 8:45
 */
@Repository("UserRepositoryInterfaceImpl")
public class UserRepositoryInterfaceImpl implements UserRepositoryInterface {

    @Autowired
    JdbcOperations jdbcOperations;

    @Override
    public void add(User user) {
        String insertSql = new String("insert into user (name, password)values (?,?)");
        jdbcOperations.update(insertSql, user.getName(), user.getPassword());
    }

    @Override
    public User findOne(long id) {
        return jdbcOperations.queryForObject("select * from user where id = 1", new UserRowMapper());
    }

    private static final class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getLong("id"), rs.getString("name"), rs.getString("password"));
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User findOneLamda(long id) {
        return jdbcOperations.queryForObject("select * from user where id = 1", (rs, rowNum) -> {
            return new User(rs.getLong("id"), rs.getString("name"), rs.getString("password"));
        });
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User findOneFunction(long id) {
        return jdbcOperations.queryForObject("select * from user where id = 1", this::userMapper);
    }

    /**
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    private User userMapper(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getLong("id"), rs.getString("name"), rs.getString("password"));
    }
}
