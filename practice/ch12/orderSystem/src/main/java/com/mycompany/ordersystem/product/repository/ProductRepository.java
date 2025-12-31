package com.mycompany.ordersystem.product.repository;

import com.mycompany.ordersystem.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ProductRepository {
    Product findById(long id);
    List<Product> findAll();
    void save(Product product);
    void update(Product product);
    void delete(long id);
}
