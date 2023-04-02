package com.qx.accounting.controller;

import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.qx.accounting.model.Goods;
import com.qx.accounting.model.Orders;
import com.qx.accounting.model.Result;
import com.qx.accounting.service.OrdersService;
import com.qx.accounting.service.impl.GoodsServiceImpl;
import com.qx.accounting.service.impl.OrdersServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * (qx_goods)表控制层
 *
 * @author xxxxx
 */
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsServiceImpl goodsService;

    @Resource
    private OrdersService ordersService;


    @PostMapping("/addGoods")
    public Result addGoods(@RequestBody Goods goods, @RequestParam Integer price,@RequestParam Integer quantity,HttpServletRequest request) {
        if (ObjectUtil.isEmpty(goods)) {
            return new Result().error("参数不全");
        }

        String token = request.getHeader("token");
        String uid = JWT.decode(token).getAudience().get(0);

        Boolean save = goodsService.save(goods);
        if (!save) {
            return new Result().error();
        }
        Integer goodsId = goods.getId();

        Orders orders = new Orders();

        orders.setUserId(Integer.valueOf(uid));
        orders.setGoodsId(goodsId);
        orders.setStatus(false);
        orders.setPrice(price);
        orders.setQuantity(quantity);

        boolean save1 = ordersService.save(orders);
        if (!save1) {
            return new Result().error();
        }


        return new Result().success();


    }
}
