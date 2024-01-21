package com.idealo.pricing;

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
            case "QuantityDiscountRule" -> new SpecialPricingRule(rule.getSpecialQuantity(), rule.getSpecialPrice());
            case "SpecialDatePricingRule" ->
                    new SpecialDatePricingRule(rule.getSpecialPrice(), rule.getDiscountPercentage(), rule.getSpecialDates());
            default -> new UnitPricingRule();
        };
    }
}
