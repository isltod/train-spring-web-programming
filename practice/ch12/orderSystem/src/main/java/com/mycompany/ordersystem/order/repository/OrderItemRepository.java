package com.mycompany.ordersystem.order.repository;

import com.mycompany.ordersystem.domain.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderItemRepository {
    @Select("SELECT * FROM order_item WHERE order_id = #{order}")
    @Results({
            @Result(id=true, property = "id", column = "order_item_id"),
            @Result(property = "quantity", column = "order_item_quantity"),
            @Result(property = "order", column = "order_id"),
            @Result(property = "product", column = "product_id",
                    one = @One(select = "com.mycompany.ordersystem.product.repository.ProductRepository.findById"))
    })
    List<OrderItem> findAll(long id);
    void save(OrderItem orderItem);
}
