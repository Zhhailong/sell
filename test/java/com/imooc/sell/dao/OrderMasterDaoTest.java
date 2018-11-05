package com.imooc.sell.dao;

import com.imooc.sell.dataobject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @Auther: zhanghailong
 * @Date: 2018/6/19 21:09
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;


    @Test
    public void saveTest() throws Exception{
        OrderMaster orderMaster =  new OrderMaster();
        orderMaster.setOrderId("12345678899");
        orderMaster.setBuyerName("师弟2");
        orderMaster.setBuyerPhone("18369658955");
        orderMaster.setBuyerAddress("天通苑ss");
        orderMaster.setBuyerOpenid("110110ss");
        orderMaster.setOrderAmount(new BigDecimal(23));
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        orderMasterDao.save(orderMaster);
    }

    @Test
    public void findByBuyerOpenid() throws Exception{
        PageRequest pageRequest = new PageRequest(0,10);
        Page<OrderMaster> orderMasters = orderMasterDao.findByBuyerOpenid("110110",pageRequest);
        System.out.println("orderMasters = >>>>>>>>>>>>>>>" + orderMasters.getContent());
    }
}