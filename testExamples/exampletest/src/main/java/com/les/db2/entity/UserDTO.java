package com.les.db2.entity;

import lombok.Data;

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
