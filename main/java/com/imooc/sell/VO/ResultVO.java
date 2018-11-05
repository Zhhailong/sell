package com.imooc.sell.VO;

import lombok.Data;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/5 15:09
 * @Description:http返回的最外层请求
 */
@Data
public class ResultVO<T> {

    /**错误码*/
    private Integer code;

    /**提示信息*/
    private String msg = "";

    /**具体内容*/
    private T data;
}
