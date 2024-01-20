package com.idealo.pricingcalculator;

import com.idealo.domain.dto.ItemDto;

public class SpecialPricingCalculator implements PricingCalculator {
    @Override
    public double calculateTotal(ItemDto item) {
        double underOfferTotal = ((double) item.getQuantity() / item.getSpecialPrice().getQty()) * item.getSpecialPrice().getPrice();
        double standardTotal = (item.getQuantity() % item.getSpecialPrice().getQty()) * item.getPrice();
        return underOfferTotal + standardTotal;
    }
}
