package com.nttdata.orderservice.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @NotEmpty(message = "A lista de itens não pode estar vazia")
    @Valid
    private List<OrderItem> items;

    @NotNull(message = "O total do pedido é obrigatório")
    private Double total;
}