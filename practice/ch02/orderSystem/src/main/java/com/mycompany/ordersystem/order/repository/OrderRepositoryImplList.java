package com.mycompany.ordersystem.order.repository;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("orderRepository")
public class OrderRepositoryImplList implements OrderRepository {

    private static final Log log = LogFactory.getLog(OrderRepositoryImplList.class);
    private List<Order> orderList;

    public OrderRepositoryImplList() {
        this.orderList = new ArrayList<Order>();
    }

    @Override
    public Order findById(Long id) {
        for (Order order : orderList) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> findAll(Customer customer) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getCustomer().getId() == customer.getId()) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    private long nextOrderId() {
        long orderId = 0;
        for (Order order : orderList) {
            if (orderId < order.getId()) {
                orderId = order.getId();
            }
        }
        return orderId + 1;
    }
    @Override
    public void save(Order order) {
        long id = nextOrderId();
        order.setId(id);
        orderList.add(order);
    }

    @Override
    public void delete(Order order) {
        for (Order order1 : orderList) {
            if (order1.getId() == order.getId()) {
                orderList.remove(order1);
                return;
            }
        }
    }
}
