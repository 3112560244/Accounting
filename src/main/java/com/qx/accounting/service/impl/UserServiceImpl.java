package com.qx.accounting.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qx.accounting.mapper.UserMapper;
import com.qx.accounting.model.User;
import com.qx.accounting.service.UserService;
import com.qx.accounting.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/31 16:29
 * @Version 1.0
 **/

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public User login(String username, String password) {

        password = MD5Util.getMD5(password);

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq(StrUtil.isNotEmpty(username), "username", username);
        queryWrapper.eq(StrUtil.isNotEmpty(password), "password", password);
        queryWrapper.eq("status", true);

        User user = userMapper.selectOne(queryWrapper);
        if (ObjectUtil.isEmpty(user)) {
            return null;
        }

        return user;
    }

}
