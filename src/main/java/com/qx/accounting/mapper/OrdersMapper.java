package com.qx.accounting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qx.accounting.model.DTO.OrderDTO;
import com.qx.accounting.model.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/31 17:14
 * @Version 1.0
 **/

public interface OrdersMapper extends BaseMapper<Orders> {

    ArrayList<OrderDTO> getUserOrder(@Param("uid") Integer uid);

    ArrayList<OrderDTO> getSellOrder(@Param("uid") Integer uid);

    //临期查看最近一个月的
    ArrayList<OrderDTO> getAdventOrder(@Param("uid") Integer uid);
}