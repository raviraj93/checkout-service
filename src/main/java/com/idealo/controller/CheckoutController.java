package com.idealo.controller;

import com.idealo.service.CheckoutService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public void scanItem(@RequestParam String sku) {
        checkoutService.scan(sku.charAt(0));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/total",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public double calculateTotal() {
        return checkoutService.total();
    }
}

