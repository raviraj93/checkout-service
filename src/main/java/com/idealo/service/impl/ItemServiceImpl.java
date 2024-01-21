package com.idealo.service.impl;

import com.idealo.domain.dto.ItemDto;
import com.idealo.mapper.ItemMapper;
import com.idealo.pricing.PricingRule;
import com.idealo.pricing.PricingRuleProvider;
import com.idealo.domain.dto.RuleConfiguration;
import com.idealo.repository.ItemRepository;
import com.idealo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final PricingRuleProvider pricingRuleProvider;
    private final List<Character> scannedItems;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(List<RuleConfiguration> pricingRules, ItemRepository itemRepository) {
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
        Map<Character, Long> itemQuantities = scannedItems.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        double sum = itemQuantities.entrySet().stream()
                .mapToDouble(entry -> calculateItemTotal(entry.getKey(), entry.getValue(), pricingRules))
                .sum();
        scannedItems.clear();
        return sum;
    }


    private double calculateItemTotal(char item, long quantity, List<RuleConfiguration> pricingRules) {
        PricingRule pricingRule = (pricingRules != null && !pricingRules.isEmpty())
                ? getPricingRuleFromList(item, pricingRules)
                : pricingRuleProvider.getPricingCalculator(item);

        ItemDto itemDto = ItemMapper.toDto(itemRepository.findByName(item));
        itemDto.setQuantity((int) quantity);
        return pricingRule.calculateTotal(itemDto);
    }

    private PricingRule getPricingRuleFromList(Character item, List<RuleConfiguration> pricingRules) {
        Optional<PricingRule> specialDateRule = pricingRules.stream()
                .filter(rule -> rule.getItemName() == item && rule.getName().equals("SpecialDatePricingRule"))
                .findFirst()
                .map(pricingRuleProvider::createPricingRule);

        if (specialDateRule.isPresent()) {
            return specialDateRule.get();
        }

        Optional<PricingRule> quantityDiscountRule = pricingRules.stream()
                .filter(rule -> rule.getItemName() == item && rule.getName().equals("QuantityDiscountRule"))
                .findFirst()
                .map(pricingRuleProvider::createPricingRule);

        return quantityDiscountRule.orElseGet(() -> pricingRuleProvider.getPricingCalculator(item));
    }

}
