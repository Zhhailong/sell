package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/6 11:33
 * @Description:
 */
@Getter
public enum PayStatusEnums {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功")
    ;

    private Integer code;

    private String msg;

    PayStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
