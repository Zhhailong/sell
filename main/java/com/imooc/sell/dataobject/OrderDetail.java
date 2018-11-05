package com.imooc.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/6 11:45
 * @Description:
 */
@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    /**订单Id*/
    private String orderId;

    /**商品Id*/
    private String productId;

    /**商品名字*/
    private String productName;

    /**商品价格*/
    private BigDecimal productPrice;

    /**数量*/
    private Integer productQuantity;

    /**小图*/
    private String productIcon;
}
