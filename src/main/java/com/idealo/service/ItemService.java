package com.idealo.service;

import com.idealo.domain.dto.RuleConfiguration;

import java.util.List;

public interface ItemService {
    void scan(char sku);

    double total(List<RuleConfiguration> pricingRules);
}
