package com.mycompany.ordersystem.inventory.repository;

import com.mycompany.ordersystem.domain.Inventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface InventoryRepository {
    Inventory findById(long id);
    void save(long id, long quantity);
    void update(long id, long quantity);
}
