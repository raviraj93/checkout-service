package com.idealo.service;

import com.idealo.domain.dto.BasketDto;
import com.idealo.domain.entity.Basket;

import java.util.List;

public interface CheckoutService {
    void scan(char item);
    double total();
}
