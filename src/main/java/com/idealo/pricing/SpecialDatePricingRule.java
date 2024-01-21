package com.idealo.pricing;

import com.idealo.domain.dto.ItemDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
public class SpecialDatePricingRule implements PricingRule {

    private final double specialPrice;
    private final int discountPercentage;
    private final List<String> specialDates;
    @Override
    public double calculateTotal(ItemDto item) {
        if (isSpecialDate()) {
            double discount = (discountPercentage / 100.0) * specialPrice;
            return (specialPrice - discount) * item.getQuantity();
        }

        return specialPrice * item.getQuantity();
    }

    private boolean isSpecialDate() {
        LocalDate today = LocalDate.now();
        return specialDates != null && specialDates.stream()
                .map(LocalDate::parse)
                .anyMatch(today::isEqual);
    }
}
