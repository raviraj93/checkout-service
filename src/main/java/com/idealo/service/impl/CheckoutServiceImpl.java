package com.idealo.service.impl;

import com.idealo.domain.dto.BasketDto;
import com.idealo.domain.dto.ItemDto;
import com.idealo.service.CheckoutService;
import com.idealo.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.idealo.pricingcalculator.PricingCalculatorProvider.getPricingCalculator;

@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {
    private ItemService itemService;
    @Override
    public BasketDto scan(List<String> items) {
        List<ItemDto> itemsDto = itemService.getItems(items);
        return BasketDto.builder()
                .items(itemsDto)
                .total(getTotal(itemsDto))
                .build();
    }

    private double getTotal(List<ItemDto> itemDtoList) {
        return itemDtoList
                .stream()
                .mapToDouble(item -> getPricingCalculator(item).calculateTotal(item))
                .sum();
    }
}
