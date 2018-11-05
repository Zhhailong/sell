package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

/**
 * @Auther: zhanghailong
 * @Date: 2018/8/11 14:35
 * @Description: 微信支付
 */
public interface PayService {

    void create(OrderDTO orderDTO);
}
