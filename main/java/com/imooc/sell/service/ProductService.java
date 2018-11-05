package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import org.springframework.data.domain.Page;

import  org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/5 13:29
 * @Description:商品
 */
public interface ProductService {

    Optional<ProductInfo> findById(String productId);

    /**查询所有在架商品*/
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**加库存*/
    void increaseStock(List<CartDTO> cartDTOList);

    /**减库存*/
    void decreaseStock(List<CartDTO> cartDTOList);

}
