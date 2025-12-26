package com.mycompany.ordersystem.inventory.service;

import com.mycompany.ordersystem.domain.Inventory;
import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.inventory.repository.InventoryRepository;
import com.mycompany.ordersystem.services.InventoryService;
import com.mycompany.ordersystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;
    private ProductService productService;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, ProductService productService) {
        this.inventoryRepository = inventoryRepository;
        this.productService = productService;
    }

    private Inventory setInventoryByProduct(long id, Product product) {
        Inventory inventory = new Inventory();
        inventory.setId(id);
        inventory.setName(product.getName());
        inventory.setPrice(product.getPrice());
        inventory.setQuantity(inventoryRepository.findById(id));
        return inventory;
    }

    @Override
    public Inventory getInventory(long id) {
        Product product = productService.getProduct(id);
        Inventory inventory = setInventoryByProduct(id, product);
        return inventory;
    }

    @Override
    public List<Inventory> getInventories() {
        List<Inventory> inventoryList = new ArrayList<>();
        List<Product> productList = productService.getProducts();
        for (Product product : productList) {
            Inventory inventory = setInventoryByProduct(product.getId(), product);
            inventoryList.add(inventory);
        }
        return inventoryList;
    }

    @Override
    public void stockInventory(long id, long quantity) {
        long inventory = inventoryRepository.findById(id);
        inventoryRepository.save(id, inventory + quantity);
    }

    @Override
    public void takeInventory(long id, long quantity) {
        long inventory = inventoryRepository.findById(id);
        if (inventory >= quantity) {
            inventoryRepository.save(id, inventory - quantity);
        }
    }
}
