package com.nttdata.orderservice.service;

import com.nttdata.orderservice.client.ProductClient;
import com.nttdata.orderservice.domain.Order;
import com.nttdata.orderservice.model.ProductResponse;
import com.nttdata.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private ProductClient productClient;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_ShouldCalculateTotalAndSetStatus() {
        // Arrange
        Order order = new Order();
        order.setProductId(1L);
        order.setCustomerId(1L);
        order.setQuantity(2);
        
        ProductResponse product = new ProductResponse();
        product.setId(1L);
        product.setNome("Produto 1");
        product.setDescricao("Descrição 1");
        product.setPreco(100.0);

        when(productClient.getProduct(1L)).thenReturn(product);

        // Act
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        
        Order createdOrder = orderService.createOrder(order);

        // Assert
        assertNotNull(createdOrder);
        assertEquals(200.0, createdOrder.getTotalAmount());
        assertEquals("PENDING", createdOrder.getStatus());
        verify(productClient).getProduct(1L);
        verify(orderRepository).save(order);
    }

    @Test
    void createOrder_WithNonExistentProduct_ShouldThrowException() {
        // Arrange
        Order order = new Order();
        order.setProductId(999L);
        order.setCustomerId(1L);
        order.setQuantity(1);

        when(productClient.getProduct(999L)).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> orderService.createOrder(order));
        verify(productClient).getProduct(999L);
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void createOrderFallback_ShouldSetFailedStatusAndZeroAmount() {
        // Arrange
        Order order = new Order();
        order.setProductId(1L);
        order.setCustomerId(1L);
        order.setQuantity(2);

        when(orderRepository.save(any(Order.class))).thenReturn(order);
        Exception exception = new RuntimeException("Service unavailable");

        // Act
        Order result = orderService.createOrderFallback(order, exception);

        // Assert
        assertNotNull(result);
        assertEquals("FAILED", result.getStatus());
        assertEquals(0.0, result.getTotalAmount());
        verify(orderRepository).save(order);
    }
}