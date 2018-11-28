package com.les.db.service;

import com.les.db.DBConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @ClassName: UserServiceTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/28 16:08
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DBConfig.class)
@Slf4j
public class UserServiceTest {
    @Autowired
    UserService userService;

    //查询某一项
    @Test
    public void selectAllName()
    {
        userService.selectAllName();
    }

    //更新
    @Test
    public void update()
    {
        userService.update();
    }

    //删除
    @Test
    public void delete()
    {
        userService.delete();
    }

    //查询所有
    @Test
    public void selectFromAll()
    {
        userService.selectFromAll();
    }


    //查询转换DTO
    @Test
    public void dtoTest()
    {
        userService.dtoTest();
    }

    @Test
    public void selectDistinctName() {
        userService.selectDistinctName();
    }

    @Test
    public void fetchFirst() {
        userService.fetchFirst();
    }

    @Test
    public void fetchOne() {
        userService.fetchOne();
    }

    @Test
    public void booleanBuilder() {
        userService.booleanBuilder();
    }
}