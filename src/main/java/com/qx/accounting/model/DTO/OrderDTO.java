package com.qx.accounting.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/4/2 19:10
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Integer id; //订单id
    private Integer quantity; //购买数量
    private Integer price;//总价格
    private Boolean status; //0-未收货 1-已收货
    private Boolean sell; //0-未卖出 1-已卖出
    private LocalDateTime createTime; //创建时间

    private String name;
    private String shopName;
    private String desc;
    private LocalDateTime makeTime; //生产日期
    private Integer expireTime;
    private LocalDateTime adventTime; //过期日期




}
