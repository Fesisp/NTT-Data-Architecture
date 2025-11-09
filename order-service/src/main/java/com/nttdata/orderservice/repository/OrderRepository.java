package com.nttdata.orderservice.repository;

import com.nttdata.orderservice.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {

    private final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0L);

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(idGenerator.incrementAndGet());
        }
        orders.put(order.getId(), order);
        return order;
    }

    public Order findById(Long id) {
        return orders.get(id);
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public List<Order> findByCustomerId(Long customerId) {
        return orders.values().stream()
                .filter(order -> order.getCustomerId().equals(customerId))
                .toList();
    }

    public void deleteById(Long id) {
        orders.remove(id);
    }
}