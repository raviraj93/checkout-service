package com.idealo.service.impl;

import com.idealo.domain.dto.RuleConfiguration;
import com.idealo.service.CheckoutService;
import com.idealo.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final ItemService itemService;
    private final PricingRuleService pricingRuleService;

    public CheckoutServiceImpl(ItemService itemService,
                               PricingRuleService pricingRuleService) {
        this.itemService = itemService;
        this.pricingRuleService = pricingRuleService;
    }

    public void scan(char item) {
        itemService.scan(item);
    }

    public double total() {
        List<RuleConfiguration> pricingRules = pricingRuleService.getAllPricingRules();
        return itemService.total(pricingRules);
    }
}
