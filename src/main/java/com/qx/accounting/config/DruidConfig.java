package com.qx.accounting.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZedQ
 * @Date: 2022/10/14 13:03
 * @Description: druid配置类
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    //因为Springboot内置了servlet容器，所以没有web.xml，替代方法就是将ServletRegistrationBean注册进去
    //加入后台监控
    @Bean  //这里其实就相当于servlet的web.xml
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean =
                new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(), "/druid/*");

        //后台需要有人登录，进行配置
        //bean.addUrlMappings(); 这个可以添加映射，我们在构造里已经写了
        //设置一些初始化参数
        Map<String, String> initParas = new HashMap<String, String>();
        initParas.put("loginUsername", "admin");//它这个账户密码是固定的
        initParas.put("loginPassword", "123456");
        //允许谁能访问
        initParas.put("allow", "");//这个值为空或没有就允许所有人访问，ip白名单
        //initParas.put("allow","localhost");//只允许本机访问，多个ip用逗号,隔开
        //initParas.put("deny","");//ip黑名单，拒绝谁访问 deny和allow同时存在优先deny
        initParas.put("resetEnable", "false");//禁用HTML页面的Reset按钮
        bean.setInitParameters(initParas);
        return bean;
    }

    //再配置一个过滤器，Servlet按上面的方式注册Filter也只能这样
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        //可以设置也可以获取,设置一个阿里巴巴的过滤器
        bean.setFilter(new WebStatFilter());
        bean.addUrlPatterns("/*");
        //可以过滤和排除哪些东西
        Map<String, String> initParams = new HashMap<String, String>();
        //把不需要监控的过滤掉,这些不进行统计
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        return bean;
    }

    /*
     * 解决druid 日志报错：discard long time none received connection:xxx
     * */
    @PostConstruct
    public void setProperties() {
        System.setProperty("druid.mysql.usePingMethod", "false");
    }


    /**
     * 3.spring监控配置基于aop
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        DruidStatInterceptor dsInterceptor = new DruidStatInterceptor();
        return dsInterceptor;
    }

    @Bean
    @Scope("prototype")
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern("com.qx.property.service.impl.*");
        return pointcut;
    }

    @Bean
    public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
        DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
        defaultPointAdvisor.setPointcut(druidStatPointcut);
        defaultPointAdvisor.setAdvice(druidStatInterceptor);
        return defaultPointAdvisor;
    }


}
