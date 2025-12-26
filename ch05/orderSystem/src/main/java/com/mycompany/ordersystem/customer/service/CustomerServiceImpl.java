package com.mycompany.ordersystem.customer.service;

import com.mycompany.ordersystem.customer.respository.CustomerRepository;
import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    // 이것도 안해도 주입되는거 같은데...
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomer(long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(long id) {
        Customer customer = getCustomer(id);
        if (customer != null) {
            customerRepository.delete(id);
        }
    }
}
