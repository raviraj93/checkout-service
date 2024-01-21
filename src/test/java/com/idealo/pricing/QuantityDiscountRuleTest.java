package com.idealo.pricing;

import com.idealo.domain.dto.ItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuantityDiscountRuleTest {

    private QuantityDiscountRule pricingRule;

    @BeforeEach
    void setUp() {
        pricingRule = new QuantityDiscountRule(5.0, 3, 25.0);
    }

    @Test
    void calculateTotal_DiscountedSetsAndRemainingItems() {
        ItemDto cartItem = new ItemDto('A', 5, 5.0);

        double total = pricingRule.calculateTotal(cartItem);
        double expected = (( 5 / 3) * 25.0 + (5 % 3) * 5.0);

        assertEquals(expected, total, 0.0001);
    }




    @Test
    void calculateTotal_NoDiscountedSets() {
        ItemDto cartItem = new ItemDto('A', 2,20.0);

        double total = pricingRule.calculateTotal(cartItem);

        double expectedTotal = 1 * 10.0;
        assertEquals(expectedTotal, total, 0.001);
    }

    @Test
    void calculateTotal_ZeroQuantity() {
        ItemDto cartItem = new ItemDto('A', 0,0.0);

        double total = pricingRule.calculateTotal(cartItem);

        double expectedTotal = 0.0;
        assertEquals(expectedTotal, total, 0.001);
    }
}
