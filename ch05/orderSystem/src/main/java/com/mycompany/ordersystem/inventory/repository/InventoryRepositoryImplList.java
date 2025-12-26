package com.mycompany.ordersystem.inventory.repository;

import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.product.repository.ProductRepositoryImplList;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("inventoryRepository")
public class InventoryRepositoryImplList implements InventoryRepository {

    private Map<Long, Long> inventories;

    public InventoryRepositoryImplList() {
        inventories = new HashMap<>();
        List<Product> products = new ProductRepositoryImplList().findAll();
        for (Product product : products) {
            long quantity = product.getId() * 10;
            inventories.put(product.getId(), quantity);
        }
    }

    @Override
    public long findById(long id) {
        Long quantity = inventories.get(id);
        return Optional.ofNullable(quantity).orElse(0L);
    }

    @Override
    public void save(long id, long quantity) {
        inventories.put(id, quantity);
    }
}
