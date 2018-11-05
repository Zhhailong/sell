package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: zhanghailong
 * @Date: 2018/8/25 16:31
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j

public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Test
    public void create() throws Exception{
        OrderDTO orderDTO = new OrderDTO();
        payService.create(orderDTO);
    }

}