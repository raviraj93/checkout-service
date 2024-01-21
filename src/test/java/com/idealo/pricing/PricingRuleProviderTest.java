package com.idealo.pricing;

import com.idealo.domain.dto.RuleConfiguration;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingRuleProviderTest {
    private static final List<String> specialDates = Collections.singletonList("2024-01-20");

    @Test
    void getPricingCalculator_shouldReturnDefaultPricingRuleWhenNoMatch() {
        List<RuleConfiguration> pricingRules = Collections.emptyList();
        PricingRuleProvider pricingRuleProvider = new PricingRuleProvider(pricingRules);

        PricingRule pricingCalculator = pricingRuleProvider.getPricingCalculator('A');

        assertEquals(UnitPricingRule.class, pricingCalculator.getClass());
    }

    @Test
    void getPricingCalculator_shouldReturnMatchingPricingRule() {
        RuleConfiguration ruleA = getQuantityDiscountRules();
        List<RuleConfiguration> pricingRules = Collections.singletonList(ruleA);
        PricingRuleProvider pricingRuleProvider = new PricingRuleProvider(pricingRules);

        PricingRule pricingCalculator = pricingRuleProvider.getPricingCalculator('A');

        assertEquals(QuantityDiscountRule.class, pricingCalculator.getClass());
    }

    @Test
    void createPricingRule_shouldReturnQuantityDiscountRule() {
        RuleConfiguration rule = getQuantityDiscountRules();
        PricingRuleProvider pricingRuleProvider = new PricingRuleProvider(Collections.emptyList());

        PricingRule pricingRule = pricingRuleProvider.createPricingRule(rule);

        assertEquals(QuantityDiscountRule.class, pricingRule.getClass());
    }

    @Test
    void createPricingRule_shouldReturnSpecialDatePricingRule() {
        RuleConfiguration rule = getSpecialDatePricingRules();
        PricingRuleProvider pricingRuleProvider = new PricingRuleProvider(Collections.emptyList());

        PricingRule pricingRule = pricingRuleProvider.createPricingRule(rule);

        assertEquals(SpecialDatePricingRule.class, pricingRule.getClass());
    }

    @Test
    void createPricingRule_shouldReturnDefaultPricingRule() {
        RuleConfiguration rule = getUnitPricingRules();
        PricingRuleProvider pricingRuleProvider = new PricingRuleProvider(Collections.emptyList());

        PricingRule pricingRule = pricingRuleProvider.createPricingRule(rule);

        assertEquals(UnitPricingRule.class, pricingRule.getClass());
    }

    private RuleConfiguration getQuantityDiscountRules() {
        return new RuleConfiguration("QuantityDiscountRule", 'A', 50.0, 1, 3, 100.0, 0, null);
    }

    private RuleConfiguration getSpecialDatePricingRules() {
        return new RuleConfiguration("SpecialDatePricingRule", 'E', 45.0, 1, 0, 45.0, 10, specialDates);
    }

    private RuleConfiguration getUnitPricingRules(){
        return new RuleConfiguration("UnitPricingRule", 'A', 50.0, 1, 0, null, null, null);
    }
}
