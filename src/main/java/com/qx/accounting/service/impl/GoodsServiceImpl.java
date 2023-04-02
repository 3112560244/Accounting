package com.qx.accounting.service.impl;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qx.accounting.mapper.GoodsMapper;
import com.qx.accounting.model.Goods;
import com.qx.accounting.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/31 16:29
 * @Version 1.0
 **/

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public Integer saveReturnId(Goods goods) {
//        @TableId(value = "id", type = IdType.AUTO)
//        private Integer id;
//        save(goods);
//        自增id要设置  type = IdType.AUTO
//        @TableId(value = "id", type = IdType.AUTO)
//        private Integer id;


        Integer id = goods.getId();
        return id;

    }
}
