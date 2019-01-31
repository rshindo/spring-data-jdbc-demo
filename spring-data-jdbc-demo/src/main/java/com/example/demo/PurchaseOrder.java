package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@ToString
@Getter
public final class PurchaseOrder {

    @Id
    private final Long id;
    private final LocalDateTime orderDatetime;
    private Set<OrderDetail> items = new HashSet<>();

    public static PurchaseOrder of(LocalDateTime orderDatetime) {
        return new PurchaseOrder(null, orderDatetime);
    }

    private PurchaseOrder(Long id, LocalDateTime orderDatetime) {
        this.id = id;
        this.orderDatetime = orderDatetime;
    }

    void addItem(int quantity, String item) {
        items.add(OrderDetail.of(quantity, item));
    }

    public PurchaseOrder withId(Long id) {
        return new PurchaseOrder(id, orderDatetime);
    }

}
