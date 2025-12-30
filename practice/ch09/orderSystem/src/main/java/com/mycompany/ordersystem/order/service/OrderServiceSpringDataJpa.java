package com.mycompany.ordersystem.order.service;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;
import com.mycompany.ordersystem.domain.OrderItem;
import com.mycompany.ordersystem.order.repository.OrderRepositorySpringDataJpa;
import com.mycompany.ordersystem.services.InventoryService;
import com.mycompany.ordersystem.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceSpringDataJpa implements OrderService {

    private OrderRepositorySpringDataJpa orderRepository;
    private InventoryService inventoryService;

    public OrderServiceSpringDataJpa(
            OrderRepositorySpringDataJpa orderRepository, InventoryService inventoryService) {
        this.orderRepository = orderRepository;
        this.inventoryService = inventoryService;
    }

    @Override
    public void purchaseOrder(Order order) {
        for (OrderItem item : order.getItems()) {
            inventoryService.takeInventory(item.getProduct().getId(), item.getQuantity());
        }
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Order order) {
        for (OrderItem item : order.getItems()) {
            inventoryService.stockInventory(item.getProduct().getId(), item.getQuantity());
        }
        orderRepository.delete(order);
    }

    @Override
    public Order getOrder(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getOrders(Customer customer) {
        return orderRepository.findByCustomerName(customer.getName());
    }
}
