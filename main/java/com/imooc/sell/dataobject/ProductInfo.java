package com.imooc.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/5 11:31
 * @Description: 商品实体
 */
@Entity
@Data
public class ProductInfo {

    @Id
    private String productId;

    /**商品名字*/
    private String productName;

    /**单价*/
    private BigDecimal productPrice;

    /**库存*/
    private Integer productStock;

    /**商品描述*/
    private String productDescription;

    /**商品小图*/
    private String productIcon;

    /**状态0正常1下架*/
    private Integer productStatus;

    /**类目编号*/
    private Integer categoryType;
}
