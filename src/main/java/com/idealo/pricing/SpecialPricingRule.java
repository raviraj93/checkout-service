package com.idealo.pricing;

import com.idealo.domain.dto.ItemDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SpecialPricingRule implements PricingRule {

    private final int specialQuantity;
    private final double specialPrice;

    @Override
    public double calculateTotal(ItemDto item) {
        int regularPriceQuantity = item.getQuantity() % specialQuantity;
        int discountedQuantity = item.getQuantity() / specialQuantity;

        return (regularPriceQuantity * 50.0) + (discountedQuantity * specialPrice);
    }
}
