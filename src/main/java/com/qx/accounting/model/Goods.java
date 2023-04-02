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
@TableName(value = "qx_goods")
public class Goods implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 店铺名称
     */
    @TableField(value = "shop_name")
    private String shopName;

    /**
     * 商品描述
     */
    @TableField(value = "`desc`")
    private String desc;

    /**
     * 生产日期
     */
    @TableField(value = "make_time")
    private LocalDateTime makeTime;

    /**
     * 保质期天数
     */
    @TableField(value = "expire_time")
    private Integer expireTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_SHOP_NAME = "shop_name";

    public static final String COL_DESC = "desc";

    public static final String COL_MAKE_TIME = "make_time";

    public static final String COL_EXPIRE_TIME = "expire_time";
}