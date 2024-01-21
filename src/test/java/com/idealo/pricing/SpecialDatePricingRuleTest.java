package com.idealo.pricing;

import com.idealo.config.DateProvider;
import com.idealo.domain.dto.ItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SpecialDatePricingRuleTest {

    @Mock
    private DateProvider dateProvider;

    private SpecialDatePricingRule pricingRule;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        pricingRule = new SpecialDatePricingRule(20.0, 10, List.of("2024-01-20"), 3, dateProvider);
    }

    @Test
    void calculateTotal_SpecialDate() {
        when(dateProvider.getCurrentDate()).thenReturn(LocalDate.parse("2024-01-20"));
        ItemDto cartItem = new ItemDto('A', 1,10.0);

        double total = pricingRule.calculateTotal(cartItem);

        double expectedTotal = (20.0 - (0.1 * 20.0)) * 3;
        assertEquals(expectedTotal, total, 0.001);
    }

    @Test
    void calculateTotal_NonSpecialDate() {
        when(dateProvider.getCurrentDate()).thenReturn(LocalDate.parse("2024-01-21"));
        ItemDto cartItem = new ItemDto('A', 1,10.0);

        double total = pricingRule.calculateTotal(cartItem);

        double expectedTotal = 20.0 * 3;
        assertEquals(expectedTotal, total, 0.001);
    }
}
