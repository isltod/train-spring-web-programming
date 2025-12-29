package com.mycompany.ordersystem.customer.service;

import com.mycompany.ordersystem.customer.respository.CustomerRepositorySpringDataJpa;
import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("customerService")
public class CustomerServiceSpringDataJpa implements CustomerService {

    private CustomerRepositorySpringDataJpa customerRepository;
    @Autowired
    public CustomerServiceSpringDataJpa(CustomerRepositorySpringDataJpa customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomer(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
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
        customerRepository.deleteById(id);
    }
}
