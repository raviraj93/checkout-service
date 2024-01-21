package com.idealo.pricing;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@Component
public class PricingRuleConfiguration {
    private List<RuleConfiguration> pricingRules;
}
