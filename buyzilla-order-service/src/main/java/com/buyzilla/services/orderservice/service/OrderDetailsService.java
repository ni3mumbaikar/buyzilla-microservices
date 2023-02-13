package com.buyzilla.services.orderservice.service;


import com.buyzilla.services.orderservice.entity.OrderDetail;
import com.buyzilla.services.orderservice.entity.Product;
import com.buyzilla.services.orderservice.vo.OrderDetailVo;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:eng_exceptions.properties")
public class OrderDetailsService {

    public static OrderDetail convertToOrderDetail(OrderDetailVo orderDetailsVoVo){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(Product.builder()
                .productID(orderDetailsVoVo.getProductID())
                .build());
        orderDetail.setQuantity(orderDetailsVoVo.getQuantity());
        return orderDetail;
    }

}
