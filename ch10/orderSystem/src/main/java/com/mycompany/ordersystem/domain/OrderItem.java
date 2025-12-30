package com.mycompany.ordersystem.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private long id;
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "order_item_quantity")
    private long quantity;
    @Transient
    private long order;

    // 여기도 JPA 사용하려면 기본 생성자 추가...
    public OrderItem() {
    }

    public OrderItem(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public long getQuantity() {
        return quantity;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", order=" + order +
                '}';
    }
}
