package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.relational.core.mapping.Column;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@ToString
@Getter
public final class PurchaseOrder {

    @Id
    private Long id;
    private String shippingAddress;
    @Column(keyColumn = "purchase_order")
    private Set<OrderItem> items = new HashSet<>();

    private PurchaseOrder(Long id, String shippingAddress) {
        this.id = id;
        this.shippingAddress = shippingAddress;
//        this.items = items;
    }

    public static PurchaseOrder of(String shippingAddress) {
//        return new PurchaseOrder(null, shippingAddress, new HashSet<>());
        return new PurchaseOrder(null, shippingAddress);
    }

    void addItem(int quantity, String product) {
        items.add(OrderItem.of(quantity, product));
    }

    public PurchaseOrder withId(Long id) {
        return new PurchaseOrder(id, shippingAddress);
    }

}
