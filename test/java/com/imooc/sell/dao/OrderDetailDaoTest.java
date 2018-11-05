package com.imooc.sell.dao;

import com.imooc.sell.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/24 15:30
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12345678");
        orderDetail.setOrderId("123456");
        orderDetail.setProductId("111111111");
        orderDetail.setProductIcon("http://xxxx.png");
        orderDetail.setProductName("尖椒鸡蛋");
        orderDetail.setProductPrice(new BigDecimal(15));
        orderDetail.setProductQuantity(10);
        OrderDetail res = orderDetailDao.save(orderDetail);
        System.out.println("res>>>>>>>>>>>>>>>>>>>=" + res);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId("123456");
        System.out.println("orderDetailList===>>>" + orderDetailList);
    }
}