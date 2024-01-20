package com.idealo.pricingcalculator;

import com.idealo.domain.dto.ItemDto;
import com.idealo.domain.entity.Item;

public interface PricingCalculator {

    double calculateTotal(ItemDto item);
}
