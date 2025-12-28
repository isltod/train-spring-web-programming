package com.mycompany.ordersystem.product.repository;

import com.mycompany.ordersystem.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("productRepository")
public class ProductRepositorySpringJDBC implements ProductRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositorySpringJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product findById(long id) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE product_id=?";
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new RowMapper<Product>() {
                        @Override
                        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Product product = new Product();
                            product.setId(rs.getLong("product_id"));
                            product.setName(rs.getString("product_name"));
                            product.setDescription(rs.getString("product_description"));
                            product.setPrice(rs.getLong("product_price"));
                            return product;
                        }
                    },
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM product ORDER BY product_id ASC";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    Product product = new Product();
                    product.setId(rs.getLong("product_id"));
                    product.setName(rs.getString("product_name"));
                    product.setDescription(rs.getString("product_description"));
                    product.setPrice(rs.getLong("product_price"));
                    return product;
                }
        );
    }

    @Override
    public void save(Product product) {
        if (findById(product.getId()) == null) {
            String sql = "INSERT INTO product (product_name, product_description, product_price) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice());
        } else {
            String sql = "UPDATE product SET " +
                    "product_name=?, product_description=?, product_price=? WHERE product_id=?";
            jdbcTemplate.update(
                    sql, product.getName(), product.getDescription(), product.getPrice(), product.getId()
            );
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM product WHERE product_id=?";
        jdbcTemplate.update(sql, id);
    }
}
