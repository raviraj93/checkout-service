package com.idealo.pricing;

import com.idealo.domain.dto.ItemDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuantityDiscountRule implements PricingRule {

    private final double unitPrice;
    private final int specialQuantity;
    private final double specialPrice;
    @Override
    public double calculateTotal(ItemDto cartItem) {
        int quantity = cartItem.getQuantity();
        int discountedSets = quantity / specialQuantity;
        int remainingItems = quantity % specialQuantity;

        return discountedSets * specialPrice + remainingItems * unitPrice;
    }
}
