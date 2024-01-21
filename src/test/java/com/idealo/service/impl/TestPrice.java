package com.idealo.service.impl;

import com.idealo.config.DateProvider;
import com.idealo.domain.entity.Item;
import com.idealo.pricing.RuleConfiguration;
import com.idealo.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestPrice {

    private ItemRepository itemRepository;
    private ItemServiceImpl itemService;
    private static final List<String> specialDates = Collections.singletonList("2024-01-20");
    DateProvider mockDateProvider;
    List<RuleConfiguration> pricingRules;

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        itemRepository = mock(ItemRepository.class);
        mockDateProvider =  Mockito.mock(DateProvider.class);
        pricingRules = new ArrayList<>();
        pricingRules.addAll(getUnitPricingRules());
        pricingRules.addAll(getQuantityDiscountRules());
        pricingRules.addAll(getSpecialDatePricingRules());

        itemService = new ItemServiceImpl(pricingRules, itemRepository);
    }

    public double calculatePrice(String goods) {
        when(itemRepository.findByName('A')).thenReturn(getItem('A', 40.0));
        when(itemRepository.findByName('B')).thenReturn(getItem('B', 50.0));
        when(itemRepository.findByName('C')).thenReturn(getItem('C', 25.0));
        when(itemRepository.findByName('D')).thenReturn(getItem('D', 20.0));
        when(itemRepository.findByName('E')).thenReturn(getItem('E', 100.0));

        for(int i=0; i<goods.length(); i++) {
            itemService.scan(goods.charAt(i));
        }
        return itemService.total(pricingRules);
    }

    @Test
    public void totals() {
        assertEquals(0, calculatePrice(""));

        assertEquals(40, calculatePrice("A"));
        assertEquals(90, calculatePrice("AB"));
        assertEquals(135, calculatePrice("CDBA"));
        assertEquals(80, calculatePrice("AA"));
        assertEquals(100, calculatePrice("AAA"));
        assertEquals(140, calculatePrice("AAAA"));
        assertEquals(180, calculatePrice("AAAAA"));
        assertEquals(200, calculatePrice("AAAAAA"));
        assertEquals(150, calculatePrice("AAAB"));
        assertEquals(180, calculatePrice("AAABB"));
        assertEquals(200, calculatePrice("AAABBD"));
        assertEquals(200, calculatePrice("DABABA"));
    }

    private List<RuleConfiguration> getQuantityDiscountRules() {
        return List.of(
                new RuleConfiguration("QuantityDiscountRule", 'A', 40.0, 1, 3, 100.0, null, null),
                new RuleConfiguration("QuantityDiscountRule", 'B', 50.0, 1, 2, 80.0, null, null),
                new RuleConfiguration("QuantityDiscountRule", 'C', 25.0, 1, 3, 100.0, null, null),
                new RuleConfiguration("QuantityDiscountRule", 'D', 20.0, 1, 3, 100.0, null, null),
                new RuleConfiguration("QuantityDiscountRule", 'E', 100.0, 1, 3, 100.0, null, null));

    }

    private List<RuleConfiguration> getSpecialDatePricingRules() {
        return List.of(
                new RuleConfiguration("SpecialDatePricingRule", 'E', 100.0, 1, null, null, null, specialDates));
    }

    private List<RuleConfiguration> getUnitPricingRules(){
        return List.of(
                new RuleConfiguration("UnitPricingRule", 'A', 40.0, 1, null, null, null, null),
                new RuleConfiguration("UnitPricingRule", 'B', 50.0, 1, null, null, null, null),
                new RuleConfiguration("UnitPricingRule", 'C', 25.0, 1, null, null, null, null),
                new RuleConfiguration("UnitPricingRule", 'D', 20.0, 1, null, null, null, null),
                new RuleConfiguration("UnitPricingRule", 'E', 100.0, 1, null, null, null, null));
    }

    private Item getItem(Character name, Double price){
        return new Item("abc", name, 1, price);
    }
}
