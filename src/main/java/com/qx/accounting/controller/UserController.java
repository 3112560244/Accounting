package com.qx.accounting.controller;

import cn.hutool.core.util.ObjectUtil;
import com.qx.accounting.model.Result;
import com.qx.accounting.model.User;
import com.qx.accounting.service.impl.UserServiceImpl;
import com.qx.accounting.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (qx_user)表控制层
 *
 * @author zedq
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserServiceImpl userService;


    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public Result login(@RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "") String password) {
        if (username.isEmpty() || password.isEmpty())
            return new Result().error("用户名或密码不能为空");
        User user = userService.login(username, password);
        if (ObjectUtil.isEmpty(user))
            return new Result().error("用户名或密码错误");

        String token = TokenUtils.genToken(user.getId().toString(), user.getPassword());
        return new Result().success(token);
    }

    @GetMapping
    public Result getUserInfo() {

        return new Result().success(userService.list());
    }
}
