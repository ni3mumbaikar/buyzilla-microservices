package com.buyzilla.services.orderservice.controller;

import com.buyzilla.services.orderservice.entity.Order;
import com.buyzilla.services.orderservice.exception.ProductNotFoundException;
import com.buyzilla.services.orderservice.exception.ShipperNotFoundException;
import com.buyzilla.services.orderservice.exception.UserNotFoundException;
import com.buyzilla.services.orderservice.service.OrderDetailsService;
import com.buyzilla.services.orderservice.service.OrderService;
import com.buyzilla.services.orderservice.vo.OrderVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/v1/orders")
@Validated
public class OrdersController {
    @Autowired
    OrderService orderService;

    OrderDetailsService orderDetailsService;

    @GetMapping()
    ResponseEntity<List<Order>> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping
    ResponseEntity<String> saveOrders(@RequestBody @Valid OrderVo orderVo) throws ParseException, UserNotFoundException, ShipperNotFoundException, ProductNotFoundException {
        System.out.println(orderVo);
        orderService.saveOrders(orderVo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{cid}")
    ResponseEntity<List<Order>> findByCustomerID(@PathVariable Integer cid){
        return ResponseEntity.ok(orderService.findByCustomerID(cid));
    }

}
