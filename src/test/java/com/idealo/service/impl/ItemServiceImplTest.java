package com.idealo.service.impl;

import com.idealo.config.DateProvider;
import com.idealo.domain.entity.Item;
import com.idealo.pricing.PricingRuleProvider;
import com.idealo.pricing.RuleConfiguration;
import com.idealo.pricing.SpecialDatePricingRule;
import com.idealo.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ItemServiceImplTest {
    private ItemRepository itemRepository;
    private ItemServiceImpl itemService;
    private PricingRuleProvider pricingRuleProvider;
    private static final List<String> specialDates = Collections.singletonList("2024-01-20");
    DateProvider mockDateProvider;

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        pricingRuleProvider = mock(PricingRuleProvider.class);
        itemRepository = mock(ItemRepository.class);
        mockDateProvider =  Mockito.mock(DateProvider.class);
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

        when(itemRepository.findByName(sku)).thenReturn(getItem('A'));

        double total = itemService.total(getUnitPricingRules());

        assertEquals(expectedTotal, total);

    }

    @Test
    void total_withQuantityDiscountRule_shouldCalculateTotalUsingQuantityDiscountRule() {
        char sku = 'A';
        double expectedTotal = 100.0;

        itemService.scan(sku);
        itemService.scan(sku);
        itemService.scan(sku);

        when(itemRepository.findByName(sku)).thenReturn(getItem('A'));

        double total = itemService.total(getQuantityDiscountRules());

        assertEquals(expectedTotal, total);
    }

    @Test
    void total_withSpecialDatePricingRule_shouldCalculateTotalUsingSpecialDatePricingRule() {
        char sku = 'E';
        double expectedTotal = 45.0;


        Mockito.when(mockDateProvider.getCurrentDate()).thenReturn(LocalDate.of(2024, 1, 20));
        itemService.scan(sku);
        when(itemRepository.findByName(sku)).thenReturn(getItem('E'));

        when(pricingRuleProvider.getPricingCalculator(eq('E')))
                .thenReturn(new SpecialDatePricingRule(45.0, 10, specialDates, 3, mockDateProvider));


        double total = itemService.total(getSpecialDatePricingRules());

        assertEquals(expectedTotal, total);
    }


    private List<RuleConfiguration> getQuantityDiscountRules() {
        return List.of(new RuleConfiguration("QuantityDiscountRule", 'A', 50.0, 1, 3, 100.0, 0, null));
    }

    private List<RuleConfiguration> getSpecialDatePricingRules() {
        return List.of(new RuleConfiguration("SpecialDatePricingRule", 'E', 45.0, 1, 0, 45.0, 10, specialDates));
    }

    private List<RuleConfiguration> getUnitPricingRules(){
        return List.of(new RuleConfiguration("UnitPricingRule", 'A', 50, 1, 0, 0, 0, null));
    }

    private Item getItem(Character name){
        return new Item("abc", name, 1, 50.0);
    }
}

