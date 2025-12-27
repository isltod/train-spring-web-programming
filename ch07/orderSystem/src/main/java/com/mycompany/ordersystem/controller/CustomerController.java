package com.mycompany.ordersystem.controller;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    private CustomerService customerService;
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/edit")
    public String edit(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @GetMapping(path = "/list")
    public String list(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "customer/list";
    }

    @GetMapping(path = "/update")
    // 스프링 6.1 부트 3.2 이후로 @RequestParam("id") 생략이 불가능해졌다고...
    public String update(Model model, @RequestParam("id") long id) {
        Customer customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @PostMapping(path = {"/edit", "/update"})
    // @ModelAttribute("customer") 는 생략해도 잘 돌아간다...
    public String save(@Valid @ModelAttribute Customer customer, BindingResult bindingResult) {
        // 그대로 놓으면 에러 내고 뻗는다..처리해야 한다...
        if (bindingResult.hasErrors()) {
            return "customer/edit";
        }
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @GetMapping(path = "/delete")
    public String delete(Model model, @RequestParam("id") long id) {
        Customer customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);
        return "customer/delete";
    }

    @PostMapping(path = "/delete")
    public String remove(@RequestParam("id") long id) {
        customerService.deleteCustomer(id);
        return "redirect:/";
    }
}
