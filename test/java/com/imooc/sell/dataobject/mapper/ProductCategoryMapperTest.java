package com.imooc.sell.dataobject.mapper;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/18 16:50
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "帅哥张");
        map.put("category_type", 101);
        int res = mapper.insertByMap(map);
        System.out.println(res+">>>>>>>>>>>>>>>>>>>>>>>>>>>>.");

    }
    @Test
    public void insertByObject() throws Exception{
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("小帅");
        productCategory.setCategoryType(103);
        int res = mapper.insertByObject(productCategory);

    }

    @Test
    public void findByproductCategory() throws Exception{
        ProductCategory productCategory= mapper.findByCategoryType(103);
        System.out.println("///////////////"+productCategory);
    }

    @Test
    public void findByCategoryName() throws Exception{
        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList = mapper.findByCategoryName("小帅");
        System.out.println("::::::::::::::::"+productCategoryList);
    }

    @Test
    public void updByCategoryType(){
        int res = mapper.updByCategoryType("风雨",103);
    }
    @Test
    public void updByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("小帅");
        productCategory.setCategoryType(103);
        int res = mapper.updByObject(productCategory);
    }

    @Test
    public void selectByCategoryType() throws Exception{
        ProductCategory productCategory = mapper.selectByCategoryType(2);
        System.out.println(productCategory + ">>>>>>>>>>>>>>>>>>>>>");
    }
}