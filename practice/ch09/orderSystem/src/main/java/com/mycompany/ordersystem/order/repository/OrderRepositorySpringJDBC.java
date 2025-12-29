package com.mycompany.ordersystem.order.repository;

import com.mycompany.ordersystem.customer.respository.CustomerRepository;
import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;
import com.mycompany.ordersystem.domain.OrderItem;
import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.product.repository.ProductRepository;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("orderRepository")
public class OrderRepositorySpringJDBC implements OrderRepository {

    private final Connection connection;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    public OrderRepositorySpringJDBC(
            DataSource dataSource, CustomerRepository customerRepository, ProductRepository productRepository
    ) throws SQLException {
        this.connection = dataSource.getConnection();
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order findById(long id) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return getOrder(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Order> findAll(Customer customer) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, customer.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = getOrder(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    private Order getOrder(ResultSet rsOrder) throws SQLException {
        String sql = "SELECT * FROM order_item WHERE order_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        long orderId = rsOrder.getLong("order_id");
        pstmt.setLong(1, orderId);
        ResultSet rs = pstmt.executeQuery();
        List<OrderItem> orderItems = new ArrayList<>();
        while (rs.next()) {
            long productId = rs.getLong("product_id");
            Product product = productRepository.findById(productId);
            long quantity = rs.getLong("order_item_quantity");
            OrderItem orderItem = new OrderItem(product, quantity);
            orderItems.add(orderItem);
        }
        long customerId = rsOrder.getLong("customer_id");
        Customer customer = customerRepository.findById(customerId);
        Order order = new Order(customer, orderItems);
        order.setId(orderId);
        order.setDate(rsOrder.getDate("order_date").toLocalDate());
        return order;
    }

    @Override
    public void save(Order order) {
        String sql = "INSERT INTO orders(customer_id) VALUES(?)";
        String[] columns = new String[]{"order_id"};
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql, columns);
            pstmt.setLong(1, order.getCustomer().getId());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                long orderId = rs.getLong(1);
                sql = "INSERT INTO order_item(order_id, product_id, order_item_quantity) VALUES(?, ?, ?)";
                for (OrderItem item : order.getItems()) {
                    pstmt = connection.prepareStatement(sql);
                    pstmt.setLong(1, orderId);
                    pstmt.setLong(2, item.getProduct().getId());
                    pstmt.setLong(3, item.getQuantity());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Order order) {
        String sql = "DELETE FROM orders WHERE order_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, order.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
