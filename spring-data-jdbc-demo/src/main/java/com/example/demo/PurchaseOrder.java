package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@ToString
@Getter
public final class PurchaseOrder {

    @Id
    private Long id;
    private String shippingAddress;
    private Set<OrderItem> items;

    private PurchaseOrder(Long id, String shippingAddress, Set<OrderItem> items) {
        this.id = id;
        this.shippingAddress = shippingAddress;
        this.items = items;
    }

    public static PurchaseOrder of(String shippingAddress) {
        return new PurchaseOrder(null, shippingAddress, new HashSet<>());
    }

    void addItem(int quantity, String product) {
        items.add(OrderItem.of(quantity, product));
    }

    public PurchaseOrder withId(Long id) {
        return new PurchaseOrder(id, shippingAddress, items);
    }

}
