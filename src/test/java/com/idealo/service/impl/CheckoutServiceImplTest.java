package com.idealo.service.impl;


import com.idealo.domain.dto.RuleConfiguration;
import com.idealo.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CheckoutServiceImplTest {

    @Mock
    private ItemService itemService;

    @Mock
    private PricingRuleService pricingRuleService;

    @InjectMocks
    private CheckoutServiceImpl checkoutService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void scan_shouldInvokeItemServiceScan() {
        char item = 'A';

        checkoutService.scan(item);

        verify(itemService, times(1)).scan(item);
    }

    @Test
    void total_shouldInvokeItemServiceTotalWithPricingRules() {
        List<RuleConfiguration> pricingRules = Collections.singletonList(new RuleConfiguration("UnitPricingRule", 'A', 50.0, 1, 0, null, null, null));

        when(pricingRuleService.getAllPricingRules()).thenReturn(pricingRules);
        when(itemService.total(pricingRules)).thenReturn(50.0);

        double total = checkoutService.total();

        assertEquals(50.0, total);

        verify(pricingRuleService, times(1)).getAllPricingRules();
        verify(itemService, times(1)).total(pricingRules);
    }
}
