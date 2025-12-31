package com.mycompany.ordersystem.order.service;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;
import com.mycompany.ordersystem.domain.OrderItem;
import com.mycompany.ordersystem.order.repository.OrderItemRepository;
import com.mycompany.ordersystem.order.repository.OrderRepository;
import com.mycompany.ordersystem.services.InventoryService;
import com.mycompany.ordersystem.services.OrderService;
import com.mycompany.ordersystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private InventoryService inventoryService;
    private ProductService productService;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, InventoryService inventoryService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.inventoryService = inventoryService;
        this.productService = productService;
    }

    @Override
    public void purchaseOrder(Order order) {
        this.orderRepository.save(order);
        for(OrderItem item : order.getItems()) {
            item.setOrder(order.getId());
            this.orderItemRepository.save(item);
            this.inventoryService.takeInventory(item.getProduct().getId(), item.getQuantity());
        }
    }

    @Override
    public void cancelOrder(Order order) {
        for(OrderItem item : order.getItems())
            this.inventoryService.stockInventory(item.getProduct().getId(), item.getQuantity());
        this.orderRepository.delete(order);
    }

    @Override
    public Order getOrder(long id) {
        Order order = this.orderRepository.findById(id);
        return order;
    }

    @Override
    public List<Order> getOrders(Customer customer) {
        List<Order> orders = this.orderRepository.findAll(customer.getId());
        return orders;
    }
}
