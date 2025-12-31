package com.mycompany.ordersystem.services;

import com.mycompany.ordersystem.domain.Customer;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface CustomerService {
    // Config에서 @EnableMethodSecurity(prePostEnabled = true) 활성을 여기서 설정하는데...
    // 역할이 ADMIN이거나 반환 객체의 email이 요청한 자의 username과 같을 때 결과 반환
    @PostAuthorize("hasRole('ADMIN') or returnObject.email == principal.username")
    Customer getCustomer(long id);
    List<Customer> getCustomers();
    // 필터를 쓰면 filterObject를 사용할 수 있는 모양...
    @PostFilter("filterObject.email == principal.username")
    List<Customer> getCustomersByName(String name);
    @PreAuthorize("hasRole('USER') and #customer.email == principal.username")
    void saveCustomer(Customer customer);
    void deleteCustomer(long id);
}
