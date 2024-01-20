package com.idealo.pricingcalculator;

import com.idealo.domain.dto.ItemDto;

import java.util.Optional;

public final class PricingCalculatorProvider {
    private static final PricingCalculator basePriceCalculator = new BasePricingCalculator();
    private static final PricingCalculator specialOfferPriceCalculator = new SpecialPricingCalculator();

    private PricingCalculatorProvider() {
        throw new UnsupportedOperationException();
    }

    public static PricingCalculator getPricingCalculator(ItemDto item) {
        if(Optional.ofNullable(item.getSpecialPrice()).isPresent()){
            return specialOfferPriceCalculator;
        }
        return basePriceCalculator;
    }
}
