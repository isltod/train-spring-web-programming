package com.mycompany.ordersystem.customer.respository;

import com.mycompany.ordersystem.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("customerRepository")
public class CustomerRepositorySpringJDBC implements CustomerRepository {

    private final Connection connection;

    @Autowired
    public CustomerRepositorySpringJDBC(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public Customer findById(long id) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getLong("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setAddress(rs.getString("customer_address"));
                customer.setEmail(rs.getString("customer_email"));
                return customer;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getLong("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setAddress(rs.getString("customer_address"));
                customer.setEmail(rs.getString("customer_email"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE customer_name=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getLong("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setAddress(rs.getString("customer_address"));
                customer.setEmail(rs.getString("customer_email"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void delete(long id) {

    }
}
