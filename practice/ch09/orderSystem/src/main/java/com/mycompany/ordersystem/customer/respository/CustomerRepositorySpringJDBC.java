package com.mycompany.ordersystem.customer.respository;

import com.mycompany.ordersystem.domain.Customer;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("customerRepository")
public class CustomerRepositorySpringJDBC implements CustomerRepository {

    private final Connection connection;

    public CustomerRepositorySpringJDBC(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public Customer findById(long id) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
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
        String sql = "SELECT * FROM customer WHERE customer_name LIKE ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "%"+name+"%");
            ResultSet rs = pstmt.executeQuery();
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
        try {
            if (findById(customer.getId()) != null) {
                update(customer);
            } else {
                insert(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insert(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (customer_name, customer_address, customer_email) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, customer.getName());
        pstmt.setString(2, customer.getAddress());
        pstmt.setString(3, customer.getEmail());
        pstmt.executeUpdate();
    }

    private void update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET customer_name=?, customer_address=?, customer_email=? WHERE customer_id=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, customer.getName());
        pstmt.setString(2, customer.getAddress());
        pstmt.setString(3, customer.getEmail());
        pstmt.setLong(4, customer.getId());
        pstmt.executeUpdate();
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
