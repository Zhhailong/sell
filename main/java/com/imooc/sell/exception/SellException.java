package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/25 22:54
 * @Description:
 */
public class SellException extends RuntimeException{

    private Integer code;

    private String message;

    public SellException(ResultEnum resultEnum) {

        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();

    }

    public SellException(Integer code,String message) {

        super(message);
        this.code = code;
    }
}
