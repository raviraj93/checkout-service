package com.idealo.service;

import com.idealo.domain.dto.ItemDto;
import com.idealo.pricing.RuleConfiguration;

import java.util.List;

public interface ItemService {
    void scan(char sku);

    double total(List<RuleConfiguration> pricingRules);
}
