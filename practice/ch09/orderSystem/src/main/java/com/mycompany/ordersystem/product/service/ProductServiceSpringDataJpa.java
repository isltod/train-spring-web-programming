package com.mycompany.ordersystem.product.service;

import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.product.repository.ProductRepositorySpringDataJpa;
import com.mycompany.ordersystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productService")
public class ProductServiceSpringDataJpa implements ProductService {

    private ProductRepositorySpringDataJpa productRepository;

    @Autowired
    public ProductServiceSpringDataJpa(ProductRepositorySpringDataJpa productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
