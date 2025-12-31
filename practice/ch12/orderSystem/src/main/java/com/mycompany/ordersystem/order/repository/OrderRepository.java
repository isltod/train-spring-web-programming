package com.mycompany.ordersystem.order.repository;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderRepository {
    Order findById(long id);
    @Select("SELECT * FROM orders WHERE customer_id = #{id}")
    @Results({
            @Result(id=true, property = "id", column = "order_id"),
            @Result(property = "date", column = "order_date"),
            @Result(property = "customer", column = "customer_id",
                    one = @One(select = "com.mycompany.ordersystem.customer.respository.CustomerRepository.findById")),
            @Result(property = "items", column = "order_id",
                    many = @Many(select = "com.mycompany.ordersystem.order.repository.OrderItemRepository.findAll"))
    })
    List<Order> findAll(long customer);
    @Insert("INSERT INTO orders(customer_id) VALUES (#{customer.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "order_id")
    void save(Order order);
    void delete(Order order);
}
