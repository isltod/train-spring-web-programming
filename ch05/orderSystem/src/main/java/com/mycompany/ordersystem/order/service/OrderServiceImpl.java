package com.mycompany.ordersystem.order.service;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;
import com.mycompany.ordersystem.domain.OrderItem;
import com.mycompany.ordersystem.order.repository.OrderRepository;
import com.mycompany.ordersystem.services.InventoryService;
import com.mycompany.ordersystem.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private InventoryService inventoryService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, InventoryService inventoryService) {
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
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> getOrders(Customer customer) {
        return orderRepository.findAll(customer);
    }
}
