package com.mycompany.ordersystem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerService customerService = context.getBean("customerService", CustomerService.class);
        List<Customer> customers = customerService.getCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.println("-----------------------------------------------------");

    //    새 고객 입력
        Customer newCustomer = new Customer();
        newCustomer.setId(6L);
        newCustomer.setName("김육");
        newCustomer.setAddress("제주시");
        newCustomer.setEmail("kim6@gmail.com");
        customerService.saveCustomer(newCustomer);
        Customer customer6 = customerService.getCustomer(6L);
        System.out.println(customer6);
        System.out.println("-----------------------------------------------------");

    //    고객 1 삭제
        customerService.deleteCustomer(1L);
        List<Customer> customerList = customerService.getCustomers();
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
        System.out.println("-----------------------------------------------------");
    }
}
