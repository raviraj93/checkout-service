package com.idealo.pricing;

import com.idealo.domain.dto.RuleConfiguration;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@Component
public class PricingRuleConfiguration {
    private List<RuleConfiguration> pricingRules;
}
