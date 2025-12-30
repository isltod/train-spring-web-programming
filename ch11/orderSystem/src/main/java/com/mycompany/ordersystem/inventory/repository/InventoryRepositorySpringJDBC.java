package com.mycompany.ordersystem.inventory.repository;

import com.mycompany.ordersystem.domain.Inventory;
import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("inventoryRepository")
public class InventoryRepositorySpringJDBC implements InventoryRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public InventoryRepositorySpringJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long findById(long id) {
        try {
            Inventory inventory = find(id);
            if (inventory != null) {
                return inventory.getQuantity();
            }
        } catch (EmptyResultDataAccessException e) {
        }
        return 0;
    }

    private Inventory find(long id) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM inventory WHERE product_id=?";
        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> {
                    Inventory inventory = new Inventory();
                    inventory.setId(rs.getLong("product_id"));
                    inventory.setQuantity(rs.getLong("inventory_quantity"));
                    return inventory;
                },
                id
        );
    }

    @Override
    public void save(long id, long quantity) {
        if (findById(id) == 0) {
            String sql = "INSERT INTO inventory (product_id, inventory_quantity) VALUES (?, ?)";
            jdbcTemplate.update(sql, id, quantity);
        } else {
            String sql = "UPDATE inventory SET inventory_quantity = ? WHERE product_id = ?";
            jdbcTemplate.update(sql, quantity, id);
        }
    }
}
