package com.qx.accounting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qx.accounting.model.User;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/31 16:29
 * @Version 1.0
 **/

public interface UserService extends IService<User> {


    User login(String username, String password);
}
