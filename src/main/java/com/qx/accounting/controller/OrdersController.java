package com.qx.accounting.controller;

import cn.hutool.core.util.ArrayUtil;
import com.auth0.jwt.JWT;
import com.qx.accounting.model.DTO.OrderDTO;
import com.qx.accounting.model.Result;
import com.qx.accounting.service.OrdersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

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


}
