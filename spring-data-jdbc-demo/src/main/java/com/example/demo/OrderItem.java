package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@EqualsAndHashCode
@ToString
@Getter
public final class OrderItem {

    @Id
    private Long id;
    private int quantity;
    private String product;

    private OrderItem(Long id, int quantity, String product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public static OrderItem of(int quantity, String product) {
        return new OrderItem(null, quantity, product);
    }

    public OrderItem withId(Long id) {
        return new OrderItem(id, quantity, product);
    }
}
