package com.idealo.controller;

import com.idealo.domain.dto.BasketDto;
import com.idealo.service.CheckoutService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
            path = "/price",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BasketDto calculateTotal(@RequestBody @NotNull List<String> itemsName) {
        return checkoutService.scan(itemsName);
    }
}

