package com.nttdata.orderservice.service;

import com.nttdata.orderservice.client.ProductClient;
import com.nttdata.orderservice.domain.Order;
import com.nttdata.orderservice.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    
    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "createOrderFallback")
    public Order createOrder(Order order) {
        var product = productClient.getProduct(order.getProductId());
        
        if (product == null) {
            log.error("Product not found for ID: {}", order.getProductId());
            throw new RuntimeException("Product not found");
        }

        Double totalAmount = product.getPreco() * order.getQuantity();
        order.setTotalAmount(totalAmount);
        order.setStatus("PENDING");
        
        log.info("Created order for product {} with total amount {}", order.getProductId(), totalAmount);
        return orderRepository.save(order);
    }

    public Order createOrderFallback(Order order, Exception e) {
        log.error("Error while creating order: {}", e.getMessage());
        order.setStatus("FAILED");
        order.setTotalAmount(0.0);
        return orderRepository.save(order);
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}