package com.mycompany.ordersystem.main.ui;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;
import com.mycompany.ordersystem.domain.OrderItem;
import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.services.CustomerService;
import com.mycompany.ordersystem.services.InventoryService;
import com.mycompany.ordersystem.services.OrderService;
import com.mycompany.ordersystem.services.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Component("orderUI")
public class OrderUI {

    private static final Log log = LogFactory.getLog(OrderUI.class);
    private Scanner scanner;
    private CustomerService customerService;
    private OrderService orderService;
    private ProductService productService;
    private InventoryService inventoryService;

    public OrderUI(
            CustomerService customerService, OrderService orderService,
            ProductService productService, InventoryService inventoryService
    ) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.productService = productService;
        this.inventoryService = inventoryService;
        this.scanner = new Scanner(System.in);
    }

    private void showCustomers() {
        System.out.println("고객 목록: ");
        List<Customer> customerList = customerService.getCustomers();
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
    }

    private OrderItem getOrderItem() {
        System.out.println("제품 목록: ");
        List<Product> productList = productService.getProducts();
        for (Product product : productList) {
            System.out.println(product);
        }
        System.out.print("제품 ID (주문하기: 99): ");
        long productId = scanner.nextLong();
        if (productId == 99) {
            return null;
        }
        Product product= productService.getProduct(productId);
        System.out.println(product);
        long inventory = inventoryService.getInventory(productId).getQuantity();
        System.out.println("재고 수량: " + inventory);
        System.out.print("주문 수량: ");
        long quantity = scanner.nextLong();
        if (inventory < quantity) {
            System.out.println("재고가 부족합니다.");
            return null;
        }
        return new OrderItem(product, quantity);
    }

    public void purchaseOrder() {
        showCustomers();
        long customerId = scanner.nextLong();
        Customer customer = customerService.getCustomer(customerId);
        System.out.println(customer);
        OrderItem orderItem = null;
        List<OrderItem> orderItemList = new ArrayList<>();
        while ((orderItem = getOrderItem()) != null) {
            orderItemList.add(orderItem);
        }
        if (orderItemList.size() == 0) {
            return;
        }
        Order order = new Order(customer, orderItemList);
        orderService.purchaseOrder(order);
        System.out.println(order);
        System.out.println("주문이 완료되었습니다.");
    }

    public void getOrder() {
        showCustomers();
        System.out.println("고객 ID: ");
        long customerId = scanner.nextLong();
        Customer customer = customerService.getCustomer(customerId);
        System.out.println(customer);
        List<Order> orderList = orderService.getOrders(customer);
        if (orderList.size() == 0) {
            System.out.println("주문 내역이 없습니다.");
            return;
        }
        for (Order order : orderList) {
            System.out.println(order);
        }
        System.out.println("주문 ID: ");
        long orderId = scanner.nextLong();
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            System.out.println("주문 내역이 없습니다.");
            return;
        }
        for (OrderItem orderItem : order.getItems()) {
            System.out.println(orderItem);
        }
    }

    public void getOrders() {
        showCustomers();
        System.out.println("고객 ID: ");
        long customerId = scanner.nextLong();
        Customer customer = customerService.getCustomer(customerId);
        System.out.println(customer);
        List<Order> orderList = orderService.getOrders(customer);
        if (orderList.size() == 0) {
            System.out.println("주문 내역이 없습니다.");
            return;
        }
        for (Order order : orderList) {
            System.out.println(order);
            for (OrderItem orderItem : order.getItems()) {
                System.out.println(orderItem);
            }
        }
    }

    public void cancelOrder() {
        showCustomers();
        System.out.println("고객 ID: ");
        long customerId = scanner.nextLong();
        Customer customer = customerService.getCustomer(customerId);
        System.out.println(customer);
        List<Order> orderList = orderService.getOrders(customer);
        if (orderList.size() == 0) {
            System.out.println("주문 내역이 없습니다.");
            return;
        }
        System.out.println("주문 ID: ");
        long orderId = scanner.nextLong();
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            System.out.println("주문 내역이 없습니다.");
            return;
        }
        orderService.cancelOrder(order);
        System.out.println(order);
        System.out.println("주문이 취소되었습니다.");
    }
}
