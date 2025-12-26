package com.mycompany.ordersystem.inventory.repository;

import com.mycompany.ordersystem.domain.Inventory;

public interface InventoryRepository {
    long findById(long id);
    void save(long id, long quantity);
}
