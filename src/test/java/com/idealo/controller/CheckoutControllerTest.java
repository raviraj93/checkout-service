package com.idealo.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.idealo.service.CheckoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CheckoutControllerTest {

    @Mock
    private CheckoutService checkoutService;

    @InjectMocks
    private CheckoutController checkoutController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(checkoutController).build();
    }

    @Test
    void scanItem_ValidRequest_ReturnsOk() throws Exception {
        mockMvc.perform(post("/v1/api/cart/scan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("sku", "A"))
                .andExpect(status().isOk());

        verify(checkoutService).scan('A');
    }

    @Test
    void calculateTotal_ValidRequest_ReturnsTotal() throws Exception {
        when(checkoutService.total()).thenReturn(50.0);

        mockMvc.perform(get("/v1/api/cart/total")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(checkoutService).total();
    }
}
