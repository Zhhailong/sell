package com.imooc.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.enums.OrderStatusEnums;
import com.imooc.sell.enums.PayStatusEnums;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/24 22:32
 * @Description:订单对象
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**买家电话*/
    private String buyerPhone;

    /**买家地址*/
    private String buyerAddress;

    /**买家微信openId*/
    private String buyerOpenid;

    /**买家订单金额*/
    private BigDecimal orderAmount;

    /**订单状态 默认为未更改*/
    private Integer orderStatus = OrderStatusEnums.NEW.getCode();

    /**支付状态*/
    private Integer payStatus = PayStatusEnums.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetailList = new ArrayList<>();

}
