package com.buyzilla.services.orderservice.service;


import com.buyzilla.services.orderservice.entity.Customer;
import com.buyzilla.services.orderservice.entity.Order;
import com.buyzilla.services.orderservice.entity.OrderDetail;
import com.buyzilla.services.orderservice.entity.Shipper;
import com.buyzilla.services.orderservice.exception.ShipperNotFoundException;
import com.buyzilla.services.orderservice.exception.UserNotFoundException;
import com.buyzilla.services.orderservice.repository.OrderRepository;
import com.buyzilla.services.orderservice.vo.OrderDetailVo;
import com.buyzilla.services.orderservice.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@PropertySource("classpath:eng_exceptions.properties")
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Environment environment;

    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }


    public void saveOrders(OrderVo orderVo) throws ParseException {
        Customer customer = restTemplate.getForObject("http://buyzilla-customer-service/api/v1/customer/findbyid/"+orderVo.getCustomerID(),Customer.class);
        if(customer!=null)
            throw new UserNotFoundException(environment.getProperty("customer_not_found"));
        Shipper shipper = restTemplate.getForObject("http://buyzilla-shipper-service/api/v1/shipper"+orderVo.getShipperID(),Shipper.class);
        if(shipper!=null)
            throw new ShipperNotFoundException(environment.getProperty("shipper_not_found"));
        Order order1 = convertToOrders(orderVo);
        orderRepository.save(order1);
    }

    static Order convertToOrders(OrderVo orderVo) throws ParseException{
        Order order = new Order();
        order.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(orderVo.getDate()));
        Customer customers = new Customer();
        customers.setCustomerID(orderVo.getCustomerID());
        order.setCustomer(customers);
        Shipper shippers = new Shipper();
        shippers.setShipperID(orderVo.getShipperID());
        order.setShipper(shippers);
        List<OrderDetail> orderDetails = new ArrayList<>(); // Generics
        for(OrderDetailVo orderDetailsVoVo : orderVo.getOrderDetailVos()){ //for-each
            orderDetails.add(OrderDetailsService.convertToOrderDetail(orderDetailsVoVo));
        }
        order.setOrderDetails(orderDetails);
        return order;
    }

    public List<Order> findByCustomerID(Integer cid){
        return orderRepository.findByCustomerCustomerID(cid);
    }

}
