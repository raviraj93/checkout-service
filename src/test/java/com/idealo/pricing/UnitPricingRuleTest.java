package com.idealo.pricing;

import com.idealo.domain.dto.ItemDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitPricingRuleTest {

    @Test
    void calculateTotal_shouldReturnCorrectTotal() {
        UnitPricingRule unitPricingRule = new UnitPricingRule();

        char sku = 'A';
        int quantity = 3;
        double price = 20.0;
        ItemDto itemDto = new ItemDto(sku, quantity, price);

        double total = unitPricingRule.calculateTotal(itemDto);

        assertEquals(quantity * price, total);
    }
}
