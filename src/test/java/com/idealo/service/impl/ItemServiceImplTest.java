package com.idealo.service.impl;

import com.idealo.domain.entity.Item;
import com.idealo.pricing.PricingRuleProvider;
import com.idealo.pricing.RuleConfiguration;
import com.idealo.pricing.SpecialDatePricingRule;
import com.idealo.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyChar;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ItemServiceImplTest {
    private ItemRepository itemRepository;
    private ItemServiceImpl itemService;
    private PricingRuleProvider pricingRuleProvider;

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        pricingRuleProvider = mock(PricingRuleProvider.class);
        itemService = new ItemServiceImpl(Collections.emptyList(), itemRepository);
    }

    @Test
    void scan_shouldAddItemToScannedItems() {
        char sku = 'A';
        itemService.scan(sku);
        List<Character> scannedItems = List.of(sku);
        assertEquals(scannedItems, itemService.getScannedItems());
    }

    @Test
    void total_withPricingRules_shouldCalculateTotalUsingPricingRules() {
        char sku = 'A';
        double expectedTotal = 50.0;

        itemService.scan(sku);

        when(itemRepository.findByName(sku)).thenReturn(getItem());

        double total = itemService.total(getUnitPricingRules());

        assertEquals(expectedTotal, total);

        verify(itemRepository, times(1)).findByName(anyChar());
    }

    @Test
    void total_withQuantityDiscountRule_shouldCalculateTotalUsingQuantityDiscountRule() {
        char sku = 'A';
        double expectedTotal = 50.0;

        itemService.scan(sku);

        when(itemRepository.findByName(sku)).thenReturn(getItem());

        double total = itemService.total(getQuantityDiscountRules());

        assertEquals(expectedTotal, total);

        verify(itemRepository, times(1)).findByName(anyChar());
    }

    @Test
    void total_withSpecialDatePricingRule_shouldCalculateTotalUsingSpecialDatePricingRule() {
        char sku = 'E';
        double expectedTotal = 45.0;

        itemService.scan(sku);

        when(itemRepository.findByName(sku)).thenReturn(getItem(sku));
        when(pricingRuleProvider.getPricingCalculator(eq('E')))
                .thenReturn(new SpecialDatePricingRule(45.0, 10, List.of("2024-01-20")));


        double total = itemService.total(getSpecialDatePricingRules());

        assertEquals(expectedTotal, total);

        verify(itemRepository, times(1)).findByName(anyChar());
        verify(pricingRuleProvider, times(1)).getPricingCalculator(anyChar());
    }


    private List<RuleConfiguration> getQuantityDiscountRules() {
        return List.of(new RuleConfiguration("QuantityDiscountRule", 'A', 3, 100.0, 0, null));
    }

    private List<RuleConfiguration> getSpecialDatePricingRules() {
        return List.of(new RuleConfiguration("SpecialDatePricingRule", 'E', 0, 0, 10, List.of("2024-01-20")));
    }



    private List<RuleConfiguration> getUnitPricingRules(){
        return List.of(new RuleConfiguration("UnitPricingRule", 'A',
                0, 0, 0, null));
    }

    private Item getItem(){
        return new Item("abc", 'A', 1, 50.0);
    }

    private Item getItem(Character name){
        return new Item("abc", name, 1, 50.0);
    }
}

