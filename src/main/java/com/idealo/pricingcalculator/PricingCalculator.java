package com.idealo.pricingcalculator;

import com.idealo.domain.dto.ItemDto;

public interface PricingCalculator {

    double calculateTotal(ItemDto item);
}
