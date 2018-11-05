package com.imooc.sell.form;

import com.imooc.sell.dataobject.OrderDetail;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import java.util.List;


/**
 * @Auther: zhanghailong
 * @Date: 2018/7/5 23:06
 * @Description:
 */
@Data
public class OrderForm {

    /**买家姓名*/
    @NotEmpty(message = "姓名必填")
    private String name;

    /**买家电话*/
    @NotEmpty(message = "电话必填")
    private String phone;

    /**买家地址*/
    @NotEmpty(message = "地址必填")
    private String address;

    /**买家openId*/
    @NotEmpty(message = "openId必填")
    private String openid;

    /**购物车*/
    @NotEmpty(message = "购物车不能为空")
    private String items;

}
