package com.idealo.service.impl;

import com.idealo.domain.dto.ItemDto;
import com.idealo.domain.entity.Item;
import com.idealo.mapper.ItemMapper;
import com.idealo.pricing.PricingRule;
import com.idealo.pricing.PricingRuleProvider;
import com.idealo.pricing.RuleConfiguration;
import com.idealo.repository.ItemRepository;
import com.idealo.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final PricingRuleProvider pricingRuleProvider;
    private final List<Character> scannedItems;

    @Autowired
    public ItemServiceImpl(List<RuleConfiguration> pricingRules,
                           ItemRepository itemRepository) {
        this.pricingRuleProvider = new PricingRuleProvider(pricingRules);
        this.scannedItems = new ArrayList<>();
        this.itemRepository = itemRepository;
    }


    public List<Character> getScannedItems() {
        return new ArrayList<>(scannedItems);
    }


    @Override
    public void scan(char sku) {
        scannedItems.add(sku);
    }

    @Override
    public double total(List<RuleConfiguration> pricingRules) {
        return scannedItems.stream()
                .map(item -> calculateItemTotal(item, pricingRules))
                .reduce(0.0, Double::sum);
    }


    private double calculateItemTotal(char item, List<RuleConfiguration> pricingRules) {
        PricingRule pricingRule = (pricingRules != null && !pricingRules.isEmpty())
                ? getPricingRuleFromList(item, pricingRules)
                : pricingRuleProvider.getPricingCalculator(item);

        return pricingRule.calculateTotal(findItemByName(item));
    }

    private ItemDto findItemByName(Character name) {
        return ItemMapper.toDto(itemRepository.findByName(name));
    }

    private PricingRule getPricingRuleFromList(Character item, List<RuleConfiguration> pricingRules) {
        return pricingRules.stream()
                .filter(rule -> rule.getItemName() == (item))
                .findFirst()
                .map(pricingRuleProvider::createPricingRule)
                .orElseGet(() -> pricingRuleProvider.getPricingCalculator(item));
    }
}
