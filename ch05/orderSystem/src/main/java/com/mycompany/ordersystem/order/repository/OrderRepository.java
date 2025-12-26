package com.mycompany.ordersystem.order.repository;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;

import java.util.List;

public interface OrderRepository {
    Order findById(Long id);
    List<Order> findAll(Customer customer);
    void save(Order order);
    void delete(Order order);
}
