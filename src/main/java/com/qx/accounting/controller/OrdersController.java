package com.qx.accounting.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.db.sql.Order;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.qx.accounting.model.DTO.OrderDTO;
import com.qx.accounting.model.Orders;
import com.qx.accounting.model.Result;
import com.qx.accounting.service.OrdersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * (qx_orders)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {
    /**
     * 服务对象
     */
    @Resource
    private OrdersService ordersService;

    //获取待收货订单
    @GetMapping("/getWaitOrder")
    public Result getWaitOrder(HttpServletRequest request){

        String token = request.getHeader("token");
        Integer uid = Integer.valueOf(JWT.decode(token).getAudience().get(0));

        ArrayList<OrderDTO> list= ordersService.getUserWaitOrder(uid);

        if(ArrayUtil.isEmpty(list)){
            return new Result().error();
        }
        return  new Result().success(list);
    }

    //获取在卖订单
    @GetMapping("/getSellOrder")
    public Result getSellOrder(HttpServletRequest request){

        String token = request.getHeader("token");
        Integer uid = Integer.valueOf(JWT.decode(token).getAudience().get(0));

        ArrayList<OrderDTO> list= ordersService.getSellOrder(uid);

        if(ArrayUtil.isEmpty(list)){
            return new Result().error();
        }
        return  new Result().success(list);
    }

    //获取临期订单
    @GetMapping("/getAdventOrder")
    public Result getAdventOrder(HttpServletRequest request){

        String token = request.getHeader("token");
        Integer uid = Integer.valueOf(JWT.decode(token).getAudience().get(0));

        ArrayList<OrderDTO> list= ordersService.getAdventOrder(uid);

        if(ArrayUtil.isEmpty(list)){
            return new Result().error();
        }
        return  new Result().success(list);
    }

    //修改订单状态
    @PostMapping("/updateOrder")
    public Result updateOrder(HttpServletRequest request, @RequestParam Integer id,@RequestParam(defaultValue = "") Boolean status,@RequestParam(defaultValue = "") Boolean sell){

        String token = request.getHeader("token");
        Integer uid = Integer.valueOf(JWT.decode(token).getAudience().get(0));

        UpdateWrapper<Orders> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",uid);
        updateWrapper.eq("id",id);
        updateWrapper.set(ObjectUtil.isNotEmpty(status),"status",status);
        updateWrapper.set(ObjectUtil.isNotEmpty(sell),"sell",sell);

        boolean update = ordersService.update(updateWrapper);
        if (!update){
            return new Result().error();
        }

        return  new Result().success();
    }




}
