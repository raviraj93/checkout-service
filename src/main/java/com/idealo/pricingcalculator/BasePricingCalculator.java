package com.idealo.pricingcalculator;

import com.idealo.domain.dto.ItemDto;

final class BasePricingCalculator implements PricingCalculator {

    @Override
    public double calculateTotal(ItemDto item) {
        return item.getQuantity() * item.getPrice();
    }
}
