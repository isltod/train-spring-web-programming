package com.mycompany.ordersystem.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "order_date")
    private LocalDate date;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    // JPA 사용할 때 생성자가 있다면 기본 생성자가 꼭 필요하다고...
    public Order() {
    }

    public Order(Customer customer, List<OrderItem> items) {
        this.customer = customer;
        this.date = LocalDate.now();
        this.items = items;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public List<OrderItem> getItems() {
        return items;
    }
    public long getTotal() {
        long total = 0;
        for(OrderItem item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", date=" + date +
                ", items=" + items +
                '}';
    }
}
