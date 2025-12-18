package com.mycompany.ordersystem.customer.respository;

import com.mycompany.ordersystem.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("customerRepository")
public class CustomerRepositoryImplList implements CustomerRepository {

    private List<Customer> customers;

    public CustomerRepositoryImplList() {
        customers = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("김일");
        customer.setAddress("서울시");
        customer.setEmail("kim1@gmail.com");
        customers.add(customer);
        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("김이");
        customer2.setAddress("부산시");
        customer2.setEmail("kim2@gmail.com");
        customers.add(customer2);
        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setName("김삼");
        customer3.setAddress("인천시");
        customer3.setEmail("kim3@gmail.com");
        customers.add(customer3);
        Customer customer4 = new Customer();
        customer4.setId(4);
        customer4.setName("김사");
        customer4.setAddress("대전시");
        customer4.setEmail("kim4@gmail.com");
        customers.add(customer4);
        Customer customer5 = new Customer();
        customer5.setId(5);
        customer5.setName("김오");
        customer5.setAddress("대구시");
        customer5.setEmail("kim5@gmail.com");
        customers.add(customer5);
    }

    @Override
    public Customer findById(long id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> founds = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                Customer found = new Customer();
                found.setId(customer.getId());
                found.setName(customer.getName());
                found.setAddress(customer.getAddress());
                found.setEmail(customer.getEmail());
                founds.add(found);
            }
        }
        return founds;
    }

    @Override
    public void save(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customer.getId()) {
                customers.set(i, customer);
                return;
            }
        }
    }

    @Override
    public void delete(long id) {
        Customer found = findById(id);
        if (found != null) {
            customers.remove(found);
        }
    }
}
