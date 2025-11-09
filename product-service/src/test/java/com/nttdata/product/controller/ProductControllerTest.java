package com.nttdata.product.controller;

import com.nttdata.product.model.Product;
import com.nttdata.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getAllProducts_ShouldReturnList() throws Exception {
        Product product = new Product(1L, "Test Product", "Description", 99.99);
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Test Product"))
                .andExpect(jsonPath("$[0].preco").value(99.99));
    }

    @Test
    public void getProductById_WhenExists_ShouldReturn() throws Exception {
        Product product = new Product(1L, "Test Product", "Description", 99.99);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Test Product"));
    }

    @Test
    public void createProduct_ShouldReturnCreated() throws Exception {
        Product product = new Product(1L, "New Product", "Description", 99.99);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"New Product\",\"descricao\":\"Description\",\"preco\":99.99}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("New Product"));
    }
}