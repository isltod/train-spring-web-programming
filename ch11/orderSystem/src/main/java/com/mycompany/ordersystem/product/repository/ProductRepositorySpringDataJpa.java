package com.mycompany.ordersystem.product.repository;

import com.mycompany.ordersystem.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositorySpringDataJpa extends JpaRepository<Product, Long> {
}
