package com.mycompany.ordersystem.inventory.service;

import com.mycompany.ordersystem.domain.Inventory;
import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.inventory.repository.InventoryRepositorySpringDataJpa;
import com.mycompany.ordersystem.services.InventoryService;
import com.mycompany.ordersystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("inventoryService")
public class InventoryServiceSpringDataJpa implements InventoryService {

    private InventoryRepositorySpringDataJpa inventoryRepository;
    private ProductService productService;

    @Autowired
    public InventoryServiceSpringDataJpa(
            InventoryRepositorySpringDataJpa inventoryRepository, ProductService productService) {
        this.inventoryRepository = inventoryRepository;
        this.productService = productService;
    }

    @Override
    public Inventory getInventory(long id) {
        Optional<Inventory> entity = inventoryRepository.findById(id);
        Product product = productService.getProduct(id);
        Inventory inventory = new Inventory();
        if (entity.isPresent()) {
            inventory = entity.get();
        } else {
            inventory = new Inventory();
            inventory.setId(id);
            inventory.setQuantity(0L);
        }
        inventory.setName(product.getName());
        inventory.setPrice(product.getPrice());
        return inventory;
    }

    @Override
    public List<Inventory> getInventories() {
        List<Product> products = productService.getProducts();
        List<Inventory> inventories = new ArrayList<>();
        for (Product product : products) {
            Inventory inventory = getInventory(product.getId());
            inventories.add(inventory);
        }
        return inventories;
    }

    @Override
    public void stockInventory(long id, long quantity) {
        Inventory inventory = getInventory(id);
        inventory.setQuantity(inventory.getQuantity() + quantity);
        inventoryRepository.save(inventory);
    }

    @Override
    public void takeInventory(long id, long quantity) {
        Inventory inventory = getInventory(id);
        if (inventory.getQuantity() >= quantity) {
            inventory.setQuantity(inventory.getQuantity() - quantity);
            inventoryRepository.save(inventory);
        }
    }
}
