package com.les.db.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;

/**
 * @ClassName: UserDTO
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/28 15:31
 */
@Data
public class UserDTO {

    private long id;
    private String name;


    public UserDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
