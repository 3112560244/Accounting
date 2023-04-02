package com.qx.accounting.config;


import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.qx.accounting.exception.ServiceException;
import com.qx.accounting.model.User;
import com.qx.accounting.service.UserService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZedQ
 * @date 2022年10月03日 21:25
 * @Description: 拦截器业务
 */

public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    //controller方法处理之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {


        //判断jwt
        String token = request.getHeader("token");


        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException("无token，请重新登录");
        }
        // 获取 token 中的 user id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException("token验证失败，请重新登录");
        } catch (IllegalArgumentException y) {
            throw new ServiceException("解密base64失败,请重新登录");
        }
        // 根据token中的userid查询数据库
        User user = userService.getById(userId);
        if (user == null) {
            throw new ServiceException("用户不存在，请重新登录");
        }
        // 用户密码加签验证 token

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException("token验证失败，请重新登录");
        }
        return true;
    }
}