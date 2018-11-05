package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/5 13:42
 * @Description: 商品状态
 */

@Getter
public enum ProductStatusEnums {

    UP(0,"在架"),
    DOWN(1,"下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnums(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}
