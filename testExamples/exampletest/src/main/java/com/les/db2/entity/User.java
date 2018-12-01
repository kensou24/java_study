package com.les.db2.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ClassName: User
 * @Description: 测试用户数据
 * @Author: king
 * @CreateDate: 2018/11/28 9:00
 */
@Data
@Entity
public class User {
    @JsonProperty
    private String name;
    @JsonProperty
    private String password;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(long id, String name, String password) {
        this.name = name;
        this.password = password;
        this.id = id;
    }
}
