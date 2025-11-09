package com.nttdata.orderservice.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Order {

    private Long id;

    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "Total amount is required")
    @Min(value = 0, message = "Total amount must be greater than or equal to 0")
    private Double totalAmount;

    @NotEmpty(message = "Status is required")
    private String status;

    // Constructor
    public Order() {
    }
    
    public Order(Long id, Long productId, Long customerId, Integer quantity, Double totalAmount, String status) {
        this.id = id;
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}