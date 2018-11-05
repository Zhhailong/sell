package com.imooc.sell.converter;

import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Auther: zhanghailong
 * @Date: 2018/7/4 22:28
 * @Description:转换器
 */
public class OrderMaster2OrderDTO {

    public static OrderDTO converter(OrderMaster orderMaster){

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasterList){

//        List<OrderDTO> orderDTOList = new ArrayList<>();
//        for(OrderMaster orderMaster: orderMasterList){
//            OrderDTO orderDTO = new OrderDTO();
//            BeanUtils.copyProperties(orderMaster,orderDTO);
//            orderDTOList.add(orderDTO);
//        }
//        return orderDTOList;

        return orderMasterList.stream().map(e ->
                converter(e)
            ).collect(Collectors.toList());
    }
}
