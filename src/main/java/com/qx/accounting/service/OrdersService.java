package com.qx.accounting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qx.accounting.model.DTO.OrderDTO;
import com.qx.accounting.model.Orders;

import java.util.ArrayList;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/31 16:29
 * @Version 1.0
 **/

public interface OrdersService extends IService<Orders> {


    ArrayList<OrderDTO> getUserWaitOrder(Integer uid);
}
