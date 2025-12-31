package com.mycompany.ordersystem.customer.respository;

import com.mycompany.ordersystem.domain.Customer;
import jakarta.persistence.Id;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerRepository {
    @Select("SELECT * FROM customer WHERE customer_id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "customer_id"),
            @Result(property = "name", column = "customer_name"),
            @Result(property = "address", column = "customer_address"),
            @Result(property = "email", column = "customer_email")
    })
    Customer findById(long id);
    @Select(
            "SELECT customer_id AS id, customer_name AS name, customer_address AS address, customer_email AS email " +
                    "FROM customer ORDER BY customer_name"
    )
    List<Customer> findAll();
    @Select("SELECT * FROM customer WHERE customer_name = #{name}")
    @ResultMap("com.mycompany.ordersystem.customer.respository.CustomerRepository.customerResult")
    List<Customer> findByName(String name);
    @Insert("INSERT INTO customer(customer_name, customer_address, customer_email) VALUES (#{name}, #{address}, #{email})")
    void save(Customer customer);
    @Update(
            "UPDATE customer " +
                    "SET customer_name = #{name}, customer_address = #{address}, customer_email = #{email} " +
                    "WHERE customer_id = #{id}")
    void update(Customer customer);
    @Delete("DELETE FROM customer WHERE customer_id = #{id}")
    void delete(long id);
}