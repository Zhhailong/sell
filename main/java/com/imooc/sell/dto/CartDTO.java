package com.imooc.sell.dto;

import lombok.Data;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/26 23:23
 * @Description:购物车对象
 */
@Data
public class CartDTO {

    /**商品id*/
    private String productId;

    /**商品数量*/
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
