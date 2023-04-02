package com.qx.accounting.utils;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 拦截器 打印日志
 * https://www.cnblogs.com/wangshen31/p/9379197.html
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

//    @Pointcut("execution(public * com.jy.rest.controller..*.*(..))")//切入点描述 这个是controller包的切入点
    @Pointcut("execution(public * com.qx.accounting.controller..*.*(..))  ")//切入点描述 这个是controller包的切入点
    public void controllerLog(){}//签名，可以理解成这个切入点的一个名称

    //请求前和请求后数据汇总
    @Around("controllerLog()") //在切入点的方法run之前要干的
    public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder是Springmvc提供来获得请求的东西
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

        // 记录下请求内容
        String reqUrl= request.getRequestURL().toString();
        log.info("################ 请求地址-reqUrl : " +reqUrl );
        String methodType = request.getMethod();
        log.info("################ 方法类型 : " +methodType);
        String paraStr = "?";
        for(String key:request.getParameterMap().keySet()){
            paraStr=paraStr+key+"="+String.valueOf(request.getParameterMap().get(key)[0])+"&";
        }

        log.info("################ 请求参数-parasStr : " + paraStr);
//        String reqJson = JSONUtil.toJsonStr(request.getParameterMap());//需要没执行完 操作
        String reqJson ="";
        if(joinPoint.getArgs()!=null && joinPoint.getArgs().length>0){
            reqJson =JSONUtil.toJsonStr(joinPoint.getArgs()[0]);
        }

        log.info("################ 请求参数-parasJson : " + reqJson);
        log.info("################ 请求类和方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+"();");

        long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - beginTime;
        log.info("################ 请求返回结果: " + JSONUtil.toJsonStr(result));

        return result;

    }

}