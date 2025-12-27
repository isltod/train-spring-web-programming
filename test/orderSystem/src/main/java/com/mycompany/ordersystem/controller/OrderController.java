package com.mycompany.ordersystem.controller;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;
import com.mycompany.ordersystem.domain.OrderItem;
import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.services.CustomerService;
import com.mycompany.ordersystem.services.OrderService;
import com.mycompany.ordersystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"order", "customer", "products", "orders"})
@RequestMapping(value = "/order")
public class OrderController {

    private CustomerService customerService;
    private ProductService productService;
    private OrderService orderService;

    @Autowired
    public OrderController(
            CustomerService customerService, ProductService productService, OrderService orderService) {
        this.customerService = customerService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping(path = "/list")
    public String list(Model model) {
        // customer, orders 상태 변수는 여기서 써야 되는데, 처리가 복잡하니 그냥 넘기는 듯...
        return "order/list_order";
    }

    @PostMapping(path = "list_order_input_customer")
    public String listOrderInputCustomer(Model model, @RequestParam("customer_name") String customerName) {
        List<Customer> customers = customerService.getCustomersByName(customerName);
        model.addAttribute("customers", customers);
        return "order/list_order";
    }

    @PostMapping(path = "input_customer")
    public String inputCustomer(Model model, @RequestParam("customer_name") String customerName) {
        List<Customer> customers = customerService.getCustomersByName(customerName);
        model.addAttribute("customers", customers);
        return "order/create_order";
    }

    @PostMapping(path = "list_order_select_customer")
    public String listOrderSelectCustomer(
            Model model, ArrayList<Order> orders, Customer customer, @RequestParam("customer_id") long id) {
        customer = customerService.getCustomer(id);
        orders = (ArrayList<Order>) orderService.getOrders(customer);
        model.addAttribute("orders", orders);
        model.addAttribute("customer", customer);
        return "order/list_order";
    }

    @GetMapping(path = "/create")
    public String create(Model model, Order order, ArrayList<Product> products) {
        Customer customer = new Customer();
        List<OrderItem> orderItems = new ArrayList<>();
        order = new Order(customer, orderItems);
        model.addAttribute("order", order);
        products = (ArrayList<Product>) productService.getProducts();
        model.addAttribute("products", products);
        return "order/create_order";
    }
}
