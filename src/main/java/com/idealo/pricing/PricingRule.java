package com.idealo.pricing;

import com.idealo.domain.dto.ItemDto;

public interface PricingRule {

    double calculateTotal(ItemDto item);
}
