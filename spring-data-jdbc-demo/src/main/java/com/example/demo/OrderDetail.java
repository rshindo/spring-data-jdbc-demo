package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@EqualsAndHashCode
@ToString
@Getter
public final class OrderDetail {

    @Id
    private final Long id;
    private final int quantity;
    private final String item;

    public static OrderDetail of(int quantity, String product) {
        return new OrderDetail(null, quantity, product);
    }

    private OrderDetail(Long id, int quantity, String item) {
        this.id = id;
        this.quantity = quantity;
        this.item = item;
    }

    public OrderDetail withId(Long id) {
        return new OrderDetail(id, quantity, item);
    }
}
