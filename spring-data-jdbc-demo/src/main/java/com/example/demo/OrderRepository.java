package com.example.demo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<PurchaseOrder, Long> {

    @Query("select count(*) from order_item")
    int countItems();

    @Query("select p.id, p.shipping_address, i.id as items_id, i.quantity as items_quantity, i.product as items_product " +
            "from purchase_order p left join order_item i on p.id = i.purchase_order")
    List<PurchaseOrder> findAllAsList();
}
