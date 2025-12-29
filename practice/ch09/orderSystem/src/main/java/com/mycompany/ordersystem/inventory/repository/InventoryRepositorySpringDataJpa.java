package com.mycompany.ordersystem.inventory.repository;

import com.mycompany.ordersystem.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepositorySpringDataJpa extends JpaRepository<Inventory, Long> {
}
