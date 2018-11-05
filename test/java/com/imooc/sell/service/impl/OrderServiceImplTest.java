package com.imooc.sell.service.impl;

import com.imooc.sell.converter.OrderMaster2OrderDTO;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: zhanghailong
 * @Date: 2018/7/4 16:42
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "110110";

    private final String ORDER_ID = "1530709023011658067";

    @Test
    public void createOrder() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("张员外");
        orderDTO.setBuyerAddress("天通苑");
        orderDTO.setBuyerPhone("12345689111");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        //购物车内容
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(3);
        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO res = orderService.createOrder(orderDTO);
        log.info("创建订单 result{}" , res);
    }

    @Test
    public void findById() {
        OrderDTO orderDTO = orderService.findById(ORDER_ID);
        log.info("查询单个订单 orderDTO{}", orderDTO);
    }

    @Test
    public void findList() {

        PageRequest pageRequest = PageRequest.of(0,10);
        Page<OrderDTO> orderDTOList = orderService.findList("110110",pageRequest);
        if(orderDTOList.getTotalElements() > 0){
           log.info("orderDTOList >>>>>>>>>>" + orderDTOList.getContent());
        }
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findById(ORDER_ID);
        OrderDTO res = orderService.cancel(orderDTO);
        log.info("关闭订单 , orderDTO={}",res);
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findById(ORDER_ID);
        OrderDTO res = orderService.finish(orderDTO);
        log.info("完成订单 , orderDTO={}",res);
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findById(ORDER_ID);
        OrderDTO res = orderService.paid(orderDTO);
        log.info("完成订单 , orderDTO={}",res);
    }
}