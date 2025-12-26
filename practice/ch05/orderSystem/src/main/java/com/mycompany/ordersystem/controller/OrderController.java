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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"order", "customer", "products", "orders"})
@RequestMapping(value = "/order")
public class OrderController {

    @GetMapping(path = "/list")
    public String list(Model model) {
        // customer, orders 상태 변수는 여기서 써야 되는데, 처리가 복잡하니 그냥 넘기는 듯...
        return "order/list_order";
    }

    @GetMapping(path = "/create")
    public String create(Model model) {
        return "order/create_order";
    }
}
