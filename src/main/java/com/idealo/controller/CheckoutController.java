package com.idealo.controller;

import com.idealo.domain.dto.BasketDto;
import com.idealo.service.CheckoutService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/cart")
@Slf4j
@AllArgsConstructor
class CheckoutController {


    private final CheckoutService checkoutService;

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/scan",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void scanItem(@RequestParam char sku) {
        checkoutService.scan(sku);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/total",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public double calculateTotal() {
        return checkoutService.total();
    }
}

