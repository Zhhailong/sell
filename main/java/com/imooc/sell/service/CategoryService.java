package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductCategory;

import java.util.List;
import java.util.Optional;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/5 10:15
 * @Description: 类目
 */
public interface CategoryService {

    Optional<ProductCategory>  findById(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory  save(ProductCategory productCategory);
}
