package com.idealo.pricing;

import com.idealo.config.DateProviderImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@Component
public class PricingRuleProvider {

    private final List<RuleConfiguration> pricingRules;

    public PricingRule getPricingCalculator(char itemName) {
        for (RuleConfiguration rule : pricingRules) {
            if (rule.getItemName() == itemName) {
                return createPricingRule(rule);
            }
        }
        return new UnitPricingRule();
    }
    public PricingRule createPricingRule(RuleConfiguration rule) {
        return switch (rule.getName()) {

            case "QuantityDiscountRule" -> new QuantityDiscountRule(rule.getUnitPrice(),
                    rule.getSpecialQuantity(), rule.getSpecialPrice(), rule.getQuantity());

            case "SpecialDatePricingRule" -> new SpecialDatePricingRule(rule.getSpecialPrice(),
                    rule.getDiscountPercentage(), rule.getSpecialDates(), rule.getQuantity(), new DateProviderImpl());

            default -> new UnitPricingRule();
        };
    }
}
