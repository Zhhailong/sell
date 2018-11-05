package com.imooc.sell.dataobject.mapper;

import com.imooc.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/18 16:43
 * @Description:
 */
public interface ProductCategoryMapper {

    @Insert("INSERT INTO product_category(category_name,category_type) " +
            "VALUES(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);


    @Insert("INSERT INTO product_category(category_name,category_type) " +
            "VALUES(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    @Select("SELECT * FROM product_category WHERE category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("SELECT * FROM product_category WHERE category_name = #{categoryName}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    @Update("UPDATE product_category SET category_name=#{categoryName} " +
            "WHERE category_type=#{categoryType}")
    int updByCategoryType(
            @Param("categoryName")
            String categoryName,
            @Param("categoryType")
            Integer categoryType);

    @Update("UPDATE product_category SET category_name=#{categoryName} " +
            "WHERE category_type=#{categoryType}")
    int updByObject(ProductCategory productCategory);

    ProductCategory selectByCategoryType(Integer categoryType);
}
