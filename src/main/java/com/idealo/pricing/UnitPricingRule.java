package com.idealo.pricing;

import com.idealo.domain.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnitPricingRule implements PricingRule {
    @Override
    public double calculateTotal(ItemDto cartItem) {
        return cartItem.getQuantity() * cartItem.getPrice();
    }
}
