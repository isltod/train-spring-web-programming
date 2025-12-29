package com.mycompany.ordersystem.services;

public interface InventoryRepository {
    long findById(long id);
    void save(long id, long quantity);
}
