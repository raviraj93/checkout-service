package com.idealo.pricing;

import com.idealo.domain.dto.ItemDto;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UnitPricingRule implements PricingRule {

    @Override
    public double calculateTotal(ItemDto item) {
        return item.getQuantity() * item.getPrice();
    }
}
