package com.mycompany.ordersystem.customer.respository;

import com.mycompany.ordersystem.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepositorySpringDataJpa extends JpaRepository<Customer,Long> {

    List<Customer> findByName(String name);

}
