package com.imooc.sell.service.impl;

import ch.qos.logback.classic.Logger;
import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/5 10:26
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findById() throws Exception{
        Optional<ProductCategory> productCategory = categoryService.findById(10);
        if(productCategory.isPresent()){
            log.info("获取查询到的实体类==============="+productCategory.get());
        }else{
            log.info("没有查询到对应的实体类");
        }

    }
    @Test
    public void findAll() throws Exception{
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception{
        List<Integer> list = Arrays.asList(2,3,5);
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(list);
        log.info("列表查询之后查询到的值"+productCategoryList.toString());
    }

    @Test
    public void save() throws Exception{
//        ProductCategory productCategory = new ProductCategory("大家好",100);
        ProductCategory productCategory = categoryService.save(new ProductCategory("大记号",2));
        log.info("保存之后返回的实体类"+productCategory.toString());
    }
}