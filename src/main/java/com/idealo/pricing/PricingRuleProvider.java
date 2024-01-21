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
            case "QuantityDiscountRule" -> new QuantityDiscountRule(
                    rule.getUnitPrice() != null ? rule.getUnitPrice(): 0,
                    rule.getSpecialQuantity()!=  null? rule.getSpecialQuantity() : 0,
                    rule.getSpecialPrice() != null ? rule.getSpecialPrice() : 0.0
            );

            case "SpecialDatePricingRule" -> new SpecialDatePricingRule(
                    rule.getSpecialPrice() != null ? rule.getSpecialPrice() : 0.0,
                    rule.getDiscountPercentage()!= null ? rule.getDiscountPercentage() : 0,
                    rule.getSpecialDates(),
                    rule.getQuantity()!= null ? rule.getQuantity(): 0,
                    new DateProviderImpl()
            );

            default -> new UnitPricingRule();
        };
    }

}
