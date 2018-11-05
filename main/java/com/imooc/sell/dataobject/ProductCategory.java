package com.imooc.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/4 17:10
 * @Description: 类目
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {

    /**订单id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //由于报错，追加括号内
    private Integer categoryId;

    /**订单名字*/
    private  String categoryName;

    /**订单类型*/
    private Integer categoryType;

    public ProductCategory() {

    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
