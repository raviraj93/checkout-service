package com.idealo.pricing;

import com.idealo.config.DateProvider;
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
    private final int quantity;
    private final DateProvider dateProvider;

    @Override
    public double calculateTotal(ItemDto cartItem) {
        if (isSpecialDate()) {
            double discount = (discountPercentage / 100.0) * specialPrice;
            return (specialPrice - discount) * quantity;
        }

        return specialPrice * quantity;
    }

    private boolean isSpecialDate() {
        LocalDate today = dateProvider.getCurrentDate();
        return specialDates != null && specialDates.stream()
                .map(LocalDate::parse)
                .anyMatch(today::isEqual);
    }
}
