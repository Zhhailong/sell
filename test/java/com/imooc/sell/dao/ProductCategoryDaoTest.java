package com.imooc.sell.dao;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/4 17:22
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

//    @Test
//    public void findOneTest(){
////根据id：1精准匹配对象，id必须是唯一主键，查出2条会报错
//        Optional<ProductCategory> one = productCategoryDao.findById(1);
//        System.out.println("one============" + one);
//
//    }

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory("生最爱",11);
        ProductCategory result = productCategoryDao.save(productCategory);
        Assert.assertNotNull(result);
//        Assert.assertNotEquals(null,result);

    }
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,3);
        List<ProductCategory> res = productCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,res.size());
    }

}