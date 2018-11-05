package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/6 11:21
 * @Description: 订单状态
 */
@Getter
public enum OrderStatusEnums {

    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消")
    ;
    private Integer code;

    private String msg;
    OrderStatusEnums(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
