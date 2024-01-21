package com.idealo.integrationtest;

import com.idealo.service.CheckoutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CheckoutControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CheckoutService checkoutService;

    @Test
    void scanItem_ValidRequest_ReturnsOk() throws Exception {
        mockMvc.perform(post("/v1/api/cart/scan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("sku", "A"))
                .andExpect(status().isOk());

    }

    @Test
    void calculateTotal_ValidRequest_ReturnsTotal() throws Exception {

        mockMvc.perform(get("/v1/api/cart/total")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void scanItemAndCalculateTotal_ValidRequest_ReturnsTotal() throws Exception {
        mockMvc.perform(post("/v1/api/cart/scan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("sku", "A"))
                .andExpect(status().isOk());

        when(checkoutService.total()).thenReturn(50.0);

        mockMvc.perform(get("/v1/api/cart/total")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(50.0));


        verify(checkoutService).scan('A');
    }
}
