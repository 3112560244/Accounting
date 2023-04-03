package com.qx.accounting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qx.accounting.mapper.GoodsMapper;
import com.qx.accounting.mapper.OrdersMapper;
import com.qx.accounting.model.DTO.OrderDTO;
import com.qx.accounting.model.Orders;
import com.qx.accounting.service.OrdersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/31 16:29
 * @Version 1.0
 **/

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public ArrayList<OrderDTO> getUserWaitOrder(Integer uid) {
        ArrayList<OrderDTO> list = ordersMapper.getUserOrder(uid);
        return list;
    }

    @Override
    public ArrayList<OrderDTO> getSellOrder(Integer uid) {
        ArrayList<OrderDTO> list = ordersMapper.getSellOrder(uid);
        return list;
    }

    @Override
    public ArrayList<OrderDTO> getAdventOrder(Integer uid) {
        ArrayList<OrderDTO> list = ordersMapper.getAdventOrder(uid);
        return list;

    }
}
