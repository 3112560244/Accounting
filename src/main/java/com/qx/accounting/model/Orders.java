package com.qx.accounting.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/31 17:14
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "qx_orders")
public class Orders implements Serializable {
    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 商品id
     */
    @TableField(value = "goods_id")
    private Integer goodsId;

    /**
     * 购买数量
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     * 总价格
     */
    @TableField(value = "price")
    private Integer price;

    /**
     * 0-未收货 1-已收货
     */
    @TableField(value = "`status`")
    private Boolean status;

    /**
     * 0-未卖出 1-已卖出
     */
    @TableField(value = "`sell`")
    private Boolean sell;


    /**
     * 创建日期
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 收货日期
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_GOODS_ID = "goods_id";

    public static final String COL_QUANTITY = "quantity";

    public static final String COL_PRICE = "price";

    public static final String COL_STATUS = "status";

    public static final String COL_SELL = "sell";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}