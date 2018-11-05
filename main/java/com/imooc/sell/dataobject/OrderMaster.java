package com.imooc.sell.dataobject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.enums.OrderStatusEnums;
import com.imooc.sell.enums.PayStatusEnums;
import com.imooc.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/6 10:50
 * @Description: 订单主表
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /**
     * 订单Id
     */
    @Id
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

//    @Transient
//    private List<OrderDetail> orderDetailList;

    /**创建时间*/
//    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**更新时间*/
//    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}


