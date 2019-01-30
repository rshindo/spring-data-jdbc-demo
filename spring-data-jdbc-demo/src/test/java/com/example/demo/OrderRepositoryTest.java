package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts = "classpath:insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:truncate.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class OrderRepositoryTest {

    static {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void createUpdateDeleteOrder() {
        PurchaseOrder purchaseOrder = PurchaseOrder.of(LocalDateTime.now());
        purchaseOrder.addItem(4, "Captain Future Comet Lego set");
        purchaseOrder.addItem(2, "Cute blue angler fish plush toy");

        PurchaseOrder saved = orderRepository.save(purchaseOrder);

        assertEquals(1, orderRepository.count());
        assertEquals(2, orderRepository.countItems());
    }


    @Test
    void createUpdateFindAll() {
        PurchaseOrder purchaseOrder = PurchaseOrder.of(LocalDateTime.now());
        purchaseOrder.addItem(4, "Captain Future Comet Lego set");
        purchaseOrder.addItem(2, "Cute blue angler fish plush toy");

        PurchaseOrder saved = orderRepository.save(purchaseOrder);

        List<PurchaseOrder> result = orderRepository.findAllAsList();
        System.out.println(result);

        Iterable<PurchaseOrder> all = orderRepository.findAll();
        System.out.println(all);
    }

    @Test
    void test() {
        PurchaseOrder purchaseOrder = PurchaseOrder.of(LocalDateTime.now());
        purchaseOrder.addItem(4, "Captain Future Comet Lego set");
        purchaseOrder.addItem(2, "Cute blue angler fish plush toy");
        orderRepository.save(purchaseOrder);

        Iterable<PurchaseOrder> result = orderRepository.findAll(); //throw MappingException !!
        result.forEach(System.out::println);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void test2() {
        PurchaseOrder order = PurchaseOrder.of(LocalDateTime.now());
        order.addItem(10, "Java本格入門");
        order = orderRepository.save(order);
        System.out.println(jdbcTemplate.queryForList("SELECT * FROM purchase_order"));
        System.out.println(jdbcTemplate.queryForList("SELECT * FROM order_detail"));


        order.addItem(20, "はじめてのSpring Boot");
        order = orderRepository.save(order);
        System.out.println(jdbcTemplate.queryForList("SELECT * FROM purchase_order"));
        System.out.println(jdbcTemplate.queryForList("SELECT * FROM order_detail"));

        orderRepository.delete(order);
        System.out.println(jdbcTemplate.queryForList("SELECT * FROM purchase_order"));
        System.out.println(jdbcTemplate.queryForList("SELECT * FROM order_detail"));
    }

}
