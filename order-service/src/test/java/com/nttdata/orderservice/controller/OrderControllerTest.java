package com.nttdata.orderservice.controller;

import com.nttdata.orderservice.domain.Order;
import com.nttdata.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_ShouldReturnCreatedOrder() {
        // Arrange
        Order order = new Order();
        order.setProductId(1L);
        order.setCustomerId(1L);
        order.setQuantity(2);

        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        // Act
        ResponseEntity<Order> response = orderController.createOrder(order);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(orderService).createOrder(order);
    }

    @Test
    void getOrder_WithValidId_ShouldReturnOrder() {
        // Arrange
        Long orderId = 1L;
        Order order = new Order();
        order.setProductId(1L);
        order.setCustomerId(1L);
        order.setQuantity(2);

        when(orderService.getOrder(orderId)).thenReturn(order);

        // Act
        ResponseEntity<Order> response = orderController.getOrder(orderId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(orderService).getOrder(orderId);
    }

    @Test
    void getOrder_WithInvalidId_ShouldReturnNotFound() {
        // Arrange
        Long orderId = 999L;
        when(orderService.getOrder(orderId)).thenReturn(null);

        // Act
        ResponseEntity<Order> response = orderController.getOrder(orderId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(orderService).getOrder(orderId);
    }
}