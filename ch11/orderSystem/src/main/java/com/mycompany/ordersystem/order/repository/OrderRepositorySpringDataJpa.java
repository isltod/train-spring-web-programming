package com.mycompany.ordersystem.order.repository;

import com.mycompany.ordersystem.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepositorySpringDataJpa extends JpaRepository<Order, Long> {
    List<Order> findByCustomerName(String name);
}
