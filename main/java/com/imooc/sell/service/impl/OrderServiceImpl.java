package com.imooc.sell.service.impl;

import com.imooc.sell.converter.OrderMaster2OrderDTO;
import com.imooc.sell.dao.OrderDetailDao;
import com.imooc.sell.dao.OrderMasterDao;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnums;
import com.imooc.sell.enums.PayStatusEnums;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.imooc.sell.utils.keyUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/24 22:41
 * @Description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    /**创建订单*/
    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {

        String orderId= keyUtil.genUniqueKey();

        BigDecimal orderAmount = new BigDecimal(0);

//        List<CartDTO> cartDTOList = new ArrayList<>();

        //1.查询商品数量,价格
        for(OrderDetail orderDetail: orderDTO.getOrderDetailList()){
            Optional<ProductInfo> productInfo = productService.findById(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2.计算订单总价
            orderAmount = productInfo.get().getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            BeanUtils.copyProperties(productInfo.get(),orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(keyUtil.genUniqueKey());
            orderDetailDao.save(orderDetail);
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }

        //3.写入数据库(写入OrderMaster OrderDetail)
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster); //属性先拷贝再往里边赋值,否则之前赋值会被替换掉
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterDao.save(orderMaster);

        //4.扣除库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
            new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }
    /**查询单个订单*/
    @Override
    public OrderDTO findById(String orderId) {

        Optional<OrderMaster> orderMaster= orderMasterDao.findById(orderId);
        if(!orderMaster.isPresent()){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster.get(),orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**根据openId查询订单列表*/
    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {

        Page<OrderMaster> orderMasterList = orderMasterDao.findByBuyerOpenid(buyerOpenId, pageable);
//        List<OrderDTO> orderDTOList = new ArrayList<>();
//        if(orderMasterList.getContent() != null) {
//            for(OrderMaster orderMaster: orderMasterList){
//                OrderDTO orderDTO = new OrderDTO();
//                BeanUtils.copyProperties(orderMaster,orderDTO);
//                List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderMaster.getOrderId());
//                orderDTO.setOrderDetailList(orderDetailList);
//                orderDTOList.add(orderDTO);
//            }
//        }
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.converter(orderMasterList.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList,pageable,orderMasterList.getTotalElements());
        return orderDTOPage;
    }

    //关闭订单
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();

        //先查询订单的状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("[取消订单]订单状态不正确 orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnums.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result = orderMasterDao.save(orderMaster);
        if(result == null){
            log.error("[取消订单]更新失败, orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返还库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[取消订单]订单中无商品详情,orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().
                                    map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                                    .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //如果已支付退款
        if(orderDTO.getPayStatus().equals(PayStatusEnums.SUCCESS.getCode())){
            //TODO
        }
        return orderDTO;
    }

    //完成订单
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //查询订单状态
        Optional<OrderMaster> orderMaster = orderMasterDao.findById(orderDTO.getOrderId());
        if(!orderMaster.get().getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("[完结订单] orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnums.FINISHED.getCode());
        OrderMaster o1 = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,o1);
        OrderMaster result = orderMasterDao.save(o1);
        if(result == null){
            log.error("[取消订单]更新失败, orderMaster={}",o1);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    //支付订单
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        Optional<OrderMaster> orderMaster = orderMasterDao.findById(orderDTO.getOrderId());
        //判断订单状态
        if(!orderMaster.get().getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("[订单支付成功] orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if(!orderMaster.get().getPayStatus().equals(PayStatusEnums.WAIT.getCode())){
            log.error("[订单支付错误] orderId={},patStatus={]",orderMaster.get().getOrderId(),orderMaster.get().getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnums.SUCCESS.getCode());
        OrderMaster o1 = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,o1);
        OrderMaster result = orderMasterDao.save(o1);
        if(result == null){
            log.error("[订单支付]更新失败, orderMaster={}",o1);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
