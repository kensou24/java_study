package com.les.db2.controller;

import com.les.db2.entity.ResultVo;
import com.les.db2.entity.User;
import com.les.db2.service.UserService;
import com.les.db2.utils.ResultVoUtil;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserController
 * @Description: 自定义解析参数Predicate
 * @Author: king
 * @CreateDate: 2018/11/29 8:48
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping
    public ResultVo user(@QuerydslPredicate(root = User.class) Predicate predicate)
    {
        if(null == predicate) {
            return ResultVoUtil.success(userService.getAllUser());
        }else
        {
            return ResultVoUtil.success(userService.search(predicate));
        }
    }



}
