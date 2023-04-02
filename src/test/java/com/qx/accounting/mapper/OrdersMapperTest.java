package com.qx.accounting.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/4/2 19:49
 * @Version 1.0
 **/
public class OrdersMapperTest {
    private static OrdersMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(OrdersMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/OrdersMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(OrdersMapper.class, builder.openSession(true));
    }

    @Test
    public void testGetUserOrder() {
        mapper.getUserOrder(1);
    }
}
