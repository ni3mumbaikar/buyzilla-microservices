package com.buyzilla.services.orderservice.repository;

import com.buyzilla.services.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomerCustomerID(Integer customerID);
}
